package com.happytimes.alisha.nyfeed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.happytimes.alisha.nyfeed.R;
import com.happytimes.alisha.nyfeed.helper.VolleySingleton;
import com.happytimes.alisha.nyfeed.model.Doc;
import com.happytimes.alisha.nyfeed.model.MultiMedia;
import com.happytimes.alisha.nyfeed.viewHolder.ArticleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alishaalam on 7/27/16.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private static final String TAG = ArticleAdapter.class.getSimpleName();
    Context mContext;
    List<Doc> articlesList = new ArrayList<>();
    ImageLoader mImageLoader;


    public ArticleAdapter(Context mContext, List<Doc> articlesList) {
        this.mContext = mContext;
        this.articlesList = articlesList;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.item_grid_content, parent,false);
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(v);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Doc article = articlesList.get(position);

        holder.vArticleHeadline.setText(article.getHeadline().getMain());


        if (mImageLoader == null)
            mImageLoader = VolleySingleton.getInstance(mContext.getApplicationContext()).getImageLoader();

        //Always show backdrop image for popular movies, irrespective of device orientation
        List<MultiMedia> list = article.getMultimedia();
        if(list.size() > 0) {

            Glide.with(mContext)
                    .load(list.get(0).getUrl())
                    .placeholder(R.drawable.ic_image_placeholder)
                    .into(holder.vArticleImage);
        }
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}
