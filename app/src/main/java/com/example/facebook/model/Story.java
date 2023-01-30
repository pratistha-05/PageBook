package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

public class Story {

    @SerializedName("userId")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("userName")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", viewCount=" + viewCount +
                ", createdTime='" + createdTime + '\'' +
                ", expiryTime='" + expiryTime + '\'' +
                '}';
    }

    @SerializedName("viewCount")
    private int viewCount;
    @SerializedName("createdTime")
    private String createdTime;
    @SerializedName("expiryTime")
    private String expiryTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

}
