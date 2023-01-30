package com.example.facebook.RetrofitBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReactionRetrofitBuilder {
    private static Retrofit instance;

    private ReactionRetrofitBuilder() {
    }
    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (ReactionRetrofitBuilder.class) {
                if (instance == null) {
//                    builder pattern changing of parameters
                    instance = new Retrofit.Builder().baseUrl("http://10.20.4.81:9016/")//TODO
                            .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();

                }
            }
        }
        return instance;
    }
}
