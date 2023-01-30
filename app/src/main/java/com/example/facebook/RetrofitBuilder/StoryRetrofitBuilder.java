package com.example.facebook.RetrofitBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoryRetrofitBuilder {
    private static Retrofit instance;

    private StoryRetrofitBuilder() {
    }
    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (StoryRetrofitBuilder.class) {
                if (instance == null) {
//                    builder pattern changing of parameters
                    instance = new Retrofit.Builder().baseUrl("http://10.20.4.81:9011/")//TODO
                            .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();

                }
            }
        }
        return instance;
    }
}
