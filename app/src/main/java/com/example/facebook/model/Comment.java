package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", postId='" + postId + '\'' +
                ", text='" + text + '\'' +
                ", commentBy='" + commentBy + '\'' +
                ", parentComment='" + parentComment + '\'' +
                '}';
    }

    @SerializedName("id")
    public String id;
    @SerializedName("postId")
    public String postId;
    @SerializedName("message")
    private String text;
    @SerializedName("commentBy")
    public String commentBy;
    @SerializedName("parentComment")
    public String parentComment="0";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }

    public String getParentComment() {
        return parentComment;
    }

    public void setParentComment(String parentComment) {
        this.parentComment = parentComment;
    }



}
