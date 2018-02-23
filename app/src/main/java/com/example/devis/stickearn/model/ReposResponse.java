package com.example.devis.stickearn.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Devis on 22/02/2018.
 */

public class ReposResponse {
    @SerializedName("name")
    @Expose
    private String reposName;
    @SerializedName("updated_at")
    @Expose
    private String reposUpdateDate;
    @SerializedName("watchers_count")
    @Expose
    private String reposWatchers;
    @SerializedName("stargazers_count")
    @Expose
    private String reposStar;
    @SerializedName("forks_count")
    @Expose
    private String reposFork;
    @SerializedName("result")
    @Expose
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getReposName() {
        return reposName;
    }

    public void setReposName(String reposName) {
        this.reposName = reposName;
    }

    public String getReposUpdateDate() {
        return reposUpdateDate;
    }

    public void setReposUpdateDate(String reposUpdateDate) {
        this.reposUpdateDate = reposUpdateDate;
    }

    public String getReposStar() {
        return reposStar;
    }

    public void setReposStar(String reposStar) {
        this.reposStar = reposStar;
    }

    public String getReposFork() {
        return reposFork;
    }

    public void setReposFork(String reposFork) {
        this.reposFork = reposFork;
    }

    public String getReposWatchers() {
        return reposWatchers;
    }

    public void setReposWatchers(String reposWatchers) {
        this.reposWatchers = reposWatchers;
    }
}
