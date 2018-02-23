package com.example.devis.stickearn.api;

import com.example.devis.stickearn.model.FollowersResponse;
import com.example.devis.stickearn.model.FollowingResponse;
import com.example.devis.stickearn.model.ItemResponse;
import com.example.devis.stickearn.model.ReposResponse;
import com.example.devis.stickearn.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Devis on 20/02/2018.
 */

public interface Service {
    @GET("/search/users")
    Call<ItemResponse> getItems(@Query("q") String user);
    @GET("/users/{user}/following")
    Call<List<FollowingResponse>> getFollowing(@Path("user") String user);
    @GET("/users/{user}/followers")
    Call<List<FollowersResponse>> getFollowers(@Path("user") String user);
    @GET("/users/{user}/repos")
    Call<List<ReposResponse>> getRepos(@Path("user") String user);
    @GET("/users/{user}")
    Call<UserResponse> getItems1(@Path("user") String user);
}
