package com.example.facebook;

import com.bumptech.glide.load.engine.Resource;
import com.example.facebook.Notification.Response;
import com.example.facebook.model.Comment;
import com.example.facebook.model.CommentsAndLikes;
import com.example.facebook.model.Like;
import com.example.facebook.model.Login;
import com.example.facebook.model.NewPostModel;
import com.example.facebook.model.Notification;
import com.example.facebook.model.PostData;
import com.example.facebook.model.SearchData;
import com.example.facebook.model.Story;
import com.example.facebook.model.UserData;
import com.example.facebook.model.UserProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IpostApi {
    @POST("login")
    Call<Login> logIn(@Body Login userData);
    @POST("user/signup")
    Call<UserData> signUp(@Body UserData userData);
    @GET("user/login/{email}")
    Call<UserData> getCred(@Path("email") String email);
    @GET("user/get-profile/{id}")
    Call<UserProfile> getProfile(@Path("id") String id);
    @GET("user/")
    Call<List<SearchData>> getUsers();
    @POST("user/update-profile")
    Call<UserProfile> updateProfile(@Body UserProfile userProfile);


    @POST("post")
    Call<NewPostModel> newPost(@Body NewPostModel newPostModel);
    @GET("post")
    Call<List<NewPostModel>> fetchPosts();
    @POST("post/add-like")
    Call<CommentsAndLikes> addLike(@Body Like like);
    @POST("post/add-dislike")
    Call<CommentsAndLikes> addDislike(@Body Like like);
    @POST("comment/add")
    Call<CommentsAndLikes> comment(@Body Comment comment);
    @GET("post/get-reactions/{id}")
    Call<CommentsAndLikes> getReactions(@Path("id") String id);

    @GET("story")
    Call<List<Story>> fetchStories();
    @POST("story")
    Call<Story> postStories(@Body Story story);


    @POST("/token/save")
    Call<com.example.facebook.Notification.Response> sendToken(@Body Response response);
    @GET("/inapp/get/{userId}")
    Call<List<Notification>> getNotification(@Path("userId")String id);
}
