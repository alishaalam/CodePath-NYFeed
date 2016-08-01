package com.happytimes.alisha.nyfeed.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.happytimes.alisha.nyfeed.R;
import com.happytimes.alisha.nyfeed.adapter.ArticleAdapter;
import com.happytimes.alisha.nyfeed.fragments.FilterDialogFragment;
import com.happytimes.alisha.nyfeed.helper.JacksonRequest;
import com.happytimes.alisha.nyfeed.helper.VolleySingleton;
import com.happytimes.alisha.nyfeed.listeners.EndlessRecyclerViewScrollListener;
import com.happytimes.alisha.nyfeed.model.ArticleCollection;
import com.happytimes.alisha.nyfeed.model.Doc;
import com.happytimes.alisha.nyfeed.model.SearchFilters;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ArticleDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ArticleListActivity extends AppCompatActivity  implements FilterDialogFragment.OnFilterSearchListener{

    private SearchFilters mFilters;

    private static final String TAG = ArticleListActivity.class.getSimpleName();
    public static final String NY_ARTICLE_SEARCH_URL = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
    public static final String SEARCH_QUERY = "?q=";
    public static String SEARCH_QUERY_TERM = "";
    public static final String PAGE = "&page=";
    public static int PAGE_NUMBER = 1;

    public static final String BEGIN_DATE = "?begin_date=";
    public static  String BEGIN_DATE_VALUE = "";
    public static final String SORT = "&sort=";
    public static String SORT_ORDER_VALUE = "";
    public static final String FILTER_QUERY = "&fq=";
    public static String NEWS_DESK_VALUES = "";
    public static final String API_KEY = "&api-key=98fbeb4d611e402ba1855e629b652a08";


    List<Doc> articlesList = new ArrayList<>();
    ArticleAdapter articleAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.item_list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ButterKnife.bind(this);
        //To reduce overdraw
        getWindow().setBackgroundDrawable(null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        initializeRecyclerView();

        //https://api.nytimes.com/svc/search/v2/articlesearch.json?begin_date=20160112&sort=oldest&fq=news_desk:(%22Education%22%20%22Health%22)&api-key=227c750bb7714fc39ef1559ef1bd8329
        //https://api.nytimes.com/svc/search/v2/articlesearch.json?begin_date=20160801&sort=oldest
        mFilters = new SearchFilters();
        BEGIN_DATE_VALUE = mFilters.getBeginDate();
        SORT_ORDER_VALUE = mFilters.getSort_order();

        StringBuffer queryBuffer = new StringBuffer();
        queryBuffer.append(NY_ARTICLE_SEARCH_URL+BEGIN_DATE+BEGIN_DATE_VALUE+SORT+SORT_ORDER_VALUE);
        if(mFilters.getNews_desk_values() != null) {
            NEWS_DESK_VALUES = mFilters.getNews_desk_values();
            queryBuffer.append(FILTER_QUERY + NEWS_DESK_VALUES);
        }
        queryBuffer.append(API_KEY);

        if(isNetworkAvailable() && isOnline()) {
            searchArticles(queryBuffer.toString());
        }else if(! isNetworkAvailable()){
            final Snackbar snackBar = Snackbar.make(recyclerView, R.string.no_connectivity_text, Snackbar.LENGTH_INDEFINITE);
            snackBar.setAction("Dismiss", v -> {
                snackBar.dismiss();
            });
            snackBar.setActionTextColor(Color.WHITE).show();
        }else if(! isOnline()){
            final Snackbar snackBar = Snackbar.make(recyclerView, R.string.no_internet_text, Snackbar.LENGTH_INDEFINITE);
            snackBar.setAction("Dismiss", v -> {
                snackBar.dismiss();
            });
            snackBar.setActionTextColor(Color.WHITE).show();
        }
    }

    private void initializeRecyclerView() {
        /*GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(glm);
*/
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);

        articleAdapter =  new ArticleAdapter(this, articlesList);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Doc mArticle = articlesList.get(position);
                Context mContext = view.getContext();
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailActivity.ARG_ITEM, Parcels.wrap(mArticle));
                mContext.startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(sglm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                PAGE_NUMBER++;
                searchArticles(NY_ARTICLE_SEARCH_URL+SEARCH_QUERY+SEARCH_QUERY_TERM+PAGE+PAGE_NUMBER+SORT+API_KEY);
            }
        });
    }

    private void searchArticles(String url) {
        JacksonRequest<ArticleCollection> jacksonRequest = new JacksonRequest<>
                (Request.Method.GET, url, null, ArticleCollection.class, responseString -> {
                    Log.d(TAG+"Response", responseString.toString());
                    parseArticleSearchResponse(responseString);
                }, error -> error.printStackTrace());

        // Adding a request (in this example, called jacksonRequest) to the RequestQueue.
        VolleySingleton.getInstance(this).addToRequestQueue(jacksonRequest, TAG);
    }

    private void parseArticleSearchResponse(ArticleCollection responseString) {
        articlesList.addAll(responseString.getResponse().getDocs());
        displayArticles();
    }

    private void displayArticles() {
        articleAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        // Create the TypeFace from the TTF asset
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Chantelli_Antiqua.ttf");
        // Assign the typeface to the view
        //searchItem.setTypeface(font);

        searchItem.expandActionView();
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                SEARCH_QUERY_TERM = query.trim();
                searchArticles(NY_ARTICLE_SEARCH_URL+SEARCH_QUERY+SEARCH_QUERY_TERM+PAGE+PAGE_NUMBER+SORT+API_KEY);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                 showEditDialog();
                 return true;
            default:
                 return super.onOptionsItemSelected(item);
        }
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        FilterDialogFragment editNameDialogFragment = FilterDialogFragment.newInstance(mFilters);
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }


    //This method is invoked in the activity after dialog is dismissed, access the filters result passed to the activity here
    @Override
    public void onUpdateFilters(SearchFilters filters) {
        // 1. Access the updated filters here and store them in member variable
        // 2. Initiate a fresh search with these filters updated and same query value
        mFilters.beginDate = filters.getBeginDate();
        mFilters.sort_order = filters.getSort_order();
        mFilters.news_desk_values = filters.getNews_desk_values();
        Log.d(TAG, "Returned values - begin date" + mFilters.beginDate + "  Sort order: " + mFilters.sort_order + " News Desk: " + mFilters.news_desk_values);
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

}
