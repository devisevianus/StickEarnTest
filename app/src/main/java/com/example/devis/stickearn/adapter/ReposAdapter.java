package com.example.devis.stickearn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devis.stickearn.R;
import com.example.devis.stickearn.model.FollowersResponse;
import com.example.devis.stickearn.model.ReposResponse;

import java.util.List;

/**
 * Created by Devis on 22/02/2018.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {
    private List<ReposResponse> items;
    private Context context;

    public ReposAdapter(Context applicationContext, List<ReposResponse> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @Override
    public ReposAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repos_data, parent, false);
        return new ReposAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReposAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getReposName());
        String date = items.get(position).getReposUpdateDate();
        String[] dateValue = date.split("T");
        holder.date.setText(dateValue[0]);
        holder.watchers.setText(items.get(position).getReposWatchers());
        holder.star.setText(items.get(position).getReposStar());
        holder.fork.setText(items.get(position).getReposFork());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, watchers, star, fork;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.reposTitle);
            date = (TextView) view.findViewById(R.id.reposDate);
            watchers = (TextView) view.findViewById(R.id.reposWatchers);
            star = (TextView) view.findViewById(R.id.repoStar);
            fork = (TextView) view.findViewById(R.id.reposFork);
        }
    }
}
