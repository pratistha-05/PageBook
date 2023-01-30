package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

public class Like {
    @SerializedName("id")
    public String id;

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("userId")
    public String userId;
    @SerializedName("status")
    public String status;
}
