package com.example.devis.stickearn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devis.stickearn.R;
import com.example.devis.stickearn.model.FollowingResponse;
import com.example.devis.stickearn.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Devis on 21/02/2018.
 */

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder> {
    private List<FollowingResponse> items;
    private Context context;

    public FollowingAdapter(Context applicationContext, List<FollowingResponse> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @Override
    public FollowingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.following_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowingAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getLogin());

        Picasso.with(context)
                .load(items.get(position).getAvatarUrl())
                .placeholder(R.drawable.load)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.followingTitle);
            imageView = (ImageView) view.findViewById(R.id.followingCover);
        }
    }
}
