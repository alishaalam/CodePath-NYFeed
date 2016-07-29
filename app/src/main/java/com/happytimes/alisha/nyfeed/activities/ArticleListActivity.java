package com.happytimes.alisha.nyfeed.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.happytimes.alisha.nyfeed.R;
import com.happytimes.alisha.nyfeed.adapter.ArticleAdapter;
import com.happytimes.alisha.nyfeed.helper.JacksonRequest;
import com.happytimes.alisha.nyfeed.helper.VolleySingleton;
import com.happytimes.alisha.nyfeed.model.ArticleCollection;
import com.happytimes.alisha.nyfeed.model.Doc;

import org.parceler.Parcels;

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
public class ArticleListActivity extends AppCompatActivity {

    private static final String TAG = ArticleListActivity.class.getSimpleName();
    public static final String NY_ARTICLE_SEARCH_URL = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
    public static final String SEARCH_QUERY = "?q=";
    public static final String SEARCH_QUERY_TERM = "Obama";
    public static final String PAGE = "&page=";
    public static final int PAGE_NUMBER = 1;
    public static final String SORT = "&sort=newest";
    public static final String API_KEY = "&api-key=98fbeb4d611e402ba1855e629b652a08";


    List<Doc> articlesList = new ArrayList<>();
    ArticleAdapter articleAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.item_list)
    RecyclerView recyclerView;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

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

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        searchArticles(NY_ARTICLE_SEARCH_URL+SEARCH_QUERY+SEARCH_QUERY_TERM+PAGE+PAGE_NUMBER+SORT+API_KEY);
    }

    private void initializeRecyclerView() {
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(glm);

        articleAdapter =  new ArticleAdapter(this, articlesList);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Doc mArticle = articlesList.get(position);
                Context mContext = view.getContext();
                //Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailActivity.ARG_ITEM, Parcels.wrap(mArticle));
                mContext.startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    private void searchArticles(String url) {
        JacksonRequest<ArticleCollection> jacksonRequest = new JacksonRequest<>
                (Request.Method.GET, url, null, ArticleCollection.class, new Response.Listener<ArticleCollection>() {
                    @Override
                    public void onResponse(ArticleCollection responseString) {
                        Log.d(TAG+"Response", responseString.toString());
                        parseArticleSearchResponse(responseString);
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

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
        searchItem.expandActionView();
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                query = query.trim();
                searchArticles(NY_ARTICLE_SEARCH_URL+SEARCH_QUERY+query+PAGE+PAGE_NUMBER+SORT+API_KEY);
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
                setupSettingsDialog();
                return true;
            case R.id.action_share:
                showShareDialog();
                return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void showShareDialog() {

    }

    private void setupSettingsDialog() {

    }
}
