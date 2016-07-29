package com.happytimes.alisha.nyfeed.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happytimes.alisha.nyfeed.R;
import com.happytimes.alisha.nyfeed.model.Doc;

import org.parceler.Parcels;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ArticleListActivity}
 * in two-pane mode (on tablets) or a {@link ArticleDetailActivity}
 * on handsets.
 */
public class ArticleDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM = "article";

    /**
     * The content this fragment is presenting.
     */
    private Doc mArticle;
    /*ImageLoader mImageLoader;
    Context mContext;
*/
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM)) {
            // Load the content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mArticle = (Doc) Parcels.unwrap(getArguments().getParcelable(ARG_ITEM));

            Activity activity = this.getActivity();
            /*CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {

            }*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the content as text in a TextView.
        if (mArticle != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mArticle.getSnippet());
        }

        return rootView;
    }


}
