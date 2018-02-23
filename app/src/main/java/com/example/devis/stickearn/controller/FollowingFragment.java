package com.example.devis.stickearn.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devis.stickearn.R;
import com.example.devis.stickearn.adapter.FollowingAdapter;
import com.example.devis.stickearn.adapter.ItemAdapter;
import com.example.devis.stickearn.api.Client;
import com.example.devis.stickearn.api.Service;
import com.example.devis.stickearn.model.FollowingResponse;
import com.example.devis.stickearn.model.Item;
import com.example.devis.stickearn.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Devis on 21/02/2018.
 */

public class FollowingFragment extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;

    public FollowingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_following, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final String username = getActivity().getIntent().getExtras().getString("login");

        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.followingFragment);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON(username);
            }
        });

        loadJSON(username);

        return v;
    }

    private void loadJSON(String query) {
        try {
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<List<FollowingResponse>> call = apiService.getFollowing(query);
            call.enqueue(new Callback<List<FollowingResponse>>() {
                @Override
                public void onResponse(Call<List<FollowingResponse>> call, Response<List<FollowingResponse>> response) {
                    List<FollowingResponse> items = response.body();
                    recyclerView.setAdapter(new FollowingAdapter(getActivity(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<List<FollowingResponse>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Error Fetching Data", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
