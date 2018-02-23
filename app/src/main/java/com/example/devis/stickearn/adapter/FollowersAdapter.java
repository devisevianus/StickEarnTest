package com.example.devis.stickearn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devis.stickearn.R;
import com.example.devis.stickearn.model.FollowersResponse;
import com.example.devis.stickearn.model.FollowingResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Devis on 22/02/2018.
 */

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.ViewHolder> {
    private List<FollowersResponse> items;
    private Context context;

    public FollowersAdapter(Context applicationContext, List<FollowersResponse> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @Override
    public FollowersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.followers_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowersAdapter.ViewHolder holder, int position) {
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
            title = (TextView) view.findViewById(R.id.followersTitle);
            imageView = (ImageView) view.findViewById(R.id.followersCover);
        }
    }
}
