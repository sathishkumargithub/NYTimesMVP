package com.android.nytimes.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.nytimes.NYTimesMainActivity;
import com.android.nytimes.R;
import com.android.nytimes.model.Artical;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sathish on 21-02-2018.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticalsViewHolder> {

    private Context mContext;
    private ArrayList<Artical> articalList;

    public ArticlesAdapter(Context mContext, ArrayList<Artical> articalList) {
        this.mContext = mContext;
        this.articalList = articalList;
    }

    @Override
    public ArticlesAdapter.ArticalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.articalview, parent, false);

        return new ArticalsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ArticalsViewHolder holder, int position) {
        final Artical artical = articalList.get(position);
        holder.mTvArticalTitle.setText(artical.getTitle());
        holder.mTvByline.setText(artical.getAuthor());
        holder.mTvPublishedDate.setText(artical.getPublished_date());
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NYTimesMainActivity) mContext).openDetailFragment(artical.getUrl());
            }
        });
        if (artical.getImageurl() != null && artical.getImageurl().length() > 0)
            Picasso.with(holder.mArticalImageView.getContext()).load(artical.getImageurl()).into(holder.mArticalImageView);
    }


    @Override
    public int getItemCount() {
        return articalList.size();
    }

    public class ArticalsViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvArticalTitle, mTvByline, mTvPublishedDate;
        public RelativeLayout mRelativeLayout;
        public ImageView mArticalImageView;

        public ArticalsViewHolder(View itemView) {
            super(itemView);
            mTvArticalTitle = (TextView) itemView.findViewById(R.id.mTvArticalName);
            mTvByline = (TextView) itemView.findViewById(R.id.mTvByline);
            mTvPublishedDate = (TextView) itemView.findViewById(R.id.mTvPublishedDate);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_article_item);
            mArticalImageView = (ImageView) itemView.findViewById(R.id.articalimage);
        }
    }


}
