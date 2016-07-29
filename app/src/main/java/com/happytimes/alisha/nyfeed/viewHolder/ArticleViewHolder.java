package com.happytimes.alisha.nyfeed.viewHolder;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.happytimes.alisha.nyfeed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alishaalam on 7/27/16.
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @Nullable @BindView(R.id.ivArticleImage)
    public ImageView vArticleImage;

    @BindView(R.id.tvArticleHeadline)
    public TextView vArticleHeadline;

   /* @Nullable @BindView(R.id.tvArticleSummary)
    public TextView vArticleSummary;*/


    public ArticleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
