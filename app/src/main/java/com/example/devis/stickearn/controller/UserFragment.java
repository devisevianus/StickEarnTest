package com.example.devis.stickearn.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.devis.stickearn.R;
import com.example.devis.stickearn.adapter.FollowersAdapter;
import com.example.devis.stickearn.adapter.ReposAdapter;
import com.example.devis.stickearn.api.Client;
import com.example.devis.stickearn.api.Service;
import com.example.devis.stickearn.model.FollowersResponse;
import com.example.devis.stickearn.model.FollowingResponse;
import com.example.devis.stickearn.model.ReposResponse;
import com.example.devis.stickearn.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Devis on 21/02/2018.
 */

public class UserFragment extends Fragment {
    TextView Link, Username, followingTotal, followersTotal,
            reposTotal, fullName, location;
    ImageView imageView;

    public UserFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        imageView = (ImageView) v.findViewById(R.id.user_image_header);
        Username = (TextView) v.findViewById(R.id.username);
        Link = (TextView) v.findViewById(R.id.link);

        followingTotal = (TextView) v.findViewById(R.id.followingTotal);
        followersTotal = (TextView) v.findViewById(R.id.followersTotal);
        reposTotal = (TextView) v.findViewById(R.id.reposTotal);
        fullName = (TextView) v.findViewById(R.id.fullName);
        location = (TextView) v.findViewById(R.id.location);

        String username = getActivity().getIntent().getExtras().getString("login");
        String avatarUrl = getActivity().getIntent().getExtras().getString("avatar_url");
        String link = getActivity().getIntent().getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        Username.setText(username);
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.load)
                .into(imageView);

        followers(username);
        following(username);
        repositories(username);
        userdetail(username);

        return v;
    }

    private void followers(String query) {
//        Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<List<FollowersResponse>> call = apiService.getFollowers(query);
            call.enqueue(new Callback<List<FollowersResponse>>() {
                @Override
                public void onResponse(Call<List<FollowersResponse>> call, Response<List<FollowersResponse>> response) {
                    List<FollowersResponse> items = response.body();
                    followersTotal.setText(String.valueOf(items.size()));
//                    swipeContainer.setRefreshing(false);
//                    pd.hide();
                }

                @Override
                public void onFailure(Call<List<FollowersResponse>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Error Fetching Data", Toast.LENGTH_SHORT).show();
//                    Disconnected.setVisibility(View.VISIBLE);
//                    pd.hide();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void following(String query) {
//        Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<List<FollowingResponse>> call = apiService.getFollowing(query);
            call.enqueue(new Callback<List<FollowingResponse>>() {
                @Override
                public void onResponse(Call<List<FollowingResponse>> call, Response<List<FollowingResponse>> response) {
                    List<FollowingResponse> items = response.body();
                    followingTotal.setText(String.valueOf(items.size()));
//                    swipeContainer.setRefreshing(false);
//                    pd.hide();
                }

                @Override
                public void onFailure(Call<List<FollowingResponse>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Error Fetching Data", Toast.LENGTH_SHORT).show();
//                    Disconnected.setVisibility(View.VISIBLE);
//                    pd.hide();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void repositories(String query) {
//        Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<List<ReposResponse>> call = apiService.getRepos(query);
            call.enqueue(new Callback<List<ReposResponse>>() {
                @Override
                public void onResponse(Call<List<ReposResponse>> call, Response<List<ReposResponse>> response) {
                    List<ReposResponse> items = response.body();
                    reposTotal.setText(String.valueOf(items.size()));
//                    swipeContainer.setRefreshing(false);
//                    pd.hide();
                }

                @Override
                public void onFailure(Call<List<ReposResponse>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Error Fetching Data", Toast.LENGTH_SHORT).show();
//                    Disconnected.setVisibility(View.VISIBLE);
//                    pd.hide();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void userdetail(String query) {
//        Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<UserResponse> call = apiService.getItems1(query);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    String full_name = response.body().getName1();
                    String location1 = response.body().getLocation();

                    fullName.setText(full_name);
                    location.setText(location1);
//                    swipeContainer.setRefreshing(false);
//                    pd.hide();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Error Fetching Data", Toast.LENGTH_SHORT).show();
//                    Disconnected.setVisibility(View.VISIBLE);
//                    pd.hide();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private Intent createShareForcastIntent() {
        String username = getActivity().getIntent().getExtras().getString("login");
        String link = getActivity().getIntent().getExtras().getString("link");
        Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                .setType("text/plain")
                .setText("Check out this awesome developer @" + username + ", " + link)
                .getIntent();
        return shareIntent;
    }
}
