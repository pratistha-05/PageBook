package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

public class Notification {

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public Notification(int id, String userId, int socialMediaId, String content) {
        this.id = id;
        this.userId = userId;
        this.socialMediaId = socialMediaId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", socialMediaId=" + socialMediaId +
                ", content='" + content + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId(int socialMediaId) {
        this.socialMediaId = socialMediaId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @SerializedName("userId")
    private String userId;

    @SerializedName("socialMediaId")
    private int socialMediaId;
    @SerializedName("content")
    private String content;
}
