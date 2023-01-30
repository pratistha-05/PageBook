package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PostData {

    @SerializedName("post_id")
    public String post_id;

    @Override
    public String toString() {
        return "PostData{" +
                "post_id='" + post_id + '\'' +
                ", image='" + image + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }

    @SerializedName("post_img")
    public String image;
    @SerializedName("post_text")
    public String caption;


    public PostData(String post_id, String image, String caption) {
        this.post_id = post_id;
        this.image = image;
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }



    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

}
