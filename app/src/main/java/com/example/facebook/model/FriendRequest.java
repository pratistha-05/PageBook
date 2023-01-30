package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

public class FriendRequest {
    @SerializedName("name")
    public String postername;

    public String getPostername() {
        return postername;
    }

    public String getMutuals() {
        return mutuals;
    }

    public void setMutuals(String mutuals) {
        this.mutuals = mutuals;
    }



    public FriendRequest(String postername, String mutuals) {
        this.postername = postername;
        this.mutuals = mutuals;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "postername='" + postername + '\'' +
                ", mutuals='" + mutuals + '\'' +
                '}';
    }

    public void setPostername(String postername) {
        this.postername = postername;
    }


    @SerializedName("mutuals")
    public String mutuals;

}
