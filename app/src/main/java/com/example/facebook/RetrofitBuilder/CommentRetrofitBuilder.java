package com.example.facebook.RetrofitBuilder;

import com.example.facebook.model.Comment;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentRetrofitBuilder {
    private static Retrofit instance;

    private CommentRetrofitBuilder() {
    }
    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (CommentRetrofitBuilder.class) {
                if (instance == null) {
//                    builder pattern changing of parameters
                    instance = new Retrofit.Builder().baseUrl("http://10.20.4.139:9016/")//TODO
                            .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();

                }
            }
        }
        return instance;
    }
}
