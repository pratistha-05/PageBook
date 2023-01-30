package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentsAndLikes {
    @SerializedName("userId")
    public String userId;
    @SerializedName("likesCount")
    public int likes;
    @SerializedName("dislikesCount")
    public int dislike;
    @SerializedName("userReaction")
    public List<Like> reaction;
    @SerializedName("comments")
    public List<Comment> comments;


//    private Integer likesCount=0;
//    private Integer dislikesCount=0;
//    private List<UserLike> userReaction=new ArrayList<>();
//    private List<Comment> comments=new ArrayList<>();
}
