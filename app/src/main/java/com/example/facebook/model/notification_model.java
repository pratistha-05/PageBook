package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

public class notification_model {
    @SerializedName("poster_name")
    public String postername;
    @SerializedName("date")
    public String date;

    @Override
    public String toString() {
        return "notification_model{" +
                "postername='" + postername + '\'' +
                ", date='" + date + '\'' +
                ", posterdp='" + posterdp + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public notification_model(String postername, String date, String posterdp, String content) {
        this.postername = postername;
        this.date = date;
        this.posterdp = posterdp;
        this.content = content;
    }

    public String getPostername() {
        return postername;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPostername(String postername) {
        this.postername = postername;
    }

    public String getPosterdp() {
        return posterdp;
    }

    public void setPosterdp(String posterdp) {
        this.posterdp = posterdp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @SerializedName("poster_dp")
    public String posterdp;
    @SerializedName("content")
    public String content;
}
