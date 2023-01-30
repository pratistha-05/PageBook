package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

public class NewPostModel {
    @SerializedName("id")
    public String id;

    @Override
    public String toString() {
        return "NewPostModel{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", caption='" + caption + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @SerializedName("userName")
    public String username;
    @SerializedName("userId")
    public String userId;
    @SerializedName("postUrl")
    public String postUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("caption")
    public String caption;
    @SerializedName("hashtag")
    public String hashtag;
    @SerializedName("date")
    public String date;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
