package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

public class ProfileFriends {
    @SerializedName("name")
    public String name;

    public String getName() {
        return name;
    }

    public ProfileFriends(String name, String friendImg) {
        this.name = name;
        this.friendImg = friendImg;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProfileFriends{" +
                "name='" + name + '\'' +
                ", friendImg='" + friendImg + '\'' +
                '}';
    }

    public String getFriendImg() {
        return friendImg;
    }

    public void setFriendImg(String friendImg) {
        this.friendImg = friendImg;
    }

    @SerializedName("friendImg")
    public String friendImg;
}
