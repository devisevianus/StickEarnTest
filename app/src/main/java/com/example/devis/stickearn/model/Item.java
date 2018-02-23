package com.example.devis.stickearn.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Devis on 20/02/2018.
 */

public class Item {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("repos_html")
    @Expose
    private String repoUrl;
    @SerializedName("following_url")
    @Expose
    private String followingUrl;
    @SerializedName("followers_url")
    @Expose
    private String followersUrl;

    public Item(String login, String avatarUrl, String htmlUrl, String repoUrl, String followingUrl, String followersUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.repoUrl = repoUrl;
        this.followingUrl = followingUrl;
        this.followersUrl = followersUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }
}
