package com.example.facebook.RetrofitBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRetrofitBuilder {

    private static Retrofit instance;

    private LoginRetrofitBuilder() {
    }
    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (LoginRetrofitBuilder.class) {
                if (instance == null) {
//                    builder pattern changing of parameters
                    instance = new Retrofit.Builder().baseUrl("http://10.20.3.120:8111/user/")//TODO
                            .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();

                }
            }
        }
        return instance;
    }
}
