package com.example.facebook.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.facebook.Adapters.FriendRequestRecyclerViewAdapter;
import com.example.facebook.Adapters.NotificationRecyclerViewAdapter;
import com.example.facebook.Adapters.StoryRecyclerViewAdapter;
import com.example.facebook.IpostApi;
import com.example.facebook.Notification.NotificationRetrofitBuilder;
import com.example.facebook.R;
import com.example.facebook.model.FriendRequest;
import com.example.facebook.model.Notification;
import com.example.facebook.model.notification_model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationPage extends Fragment {
    List<Notification> notificationArrayList=new ArrayList<>();
    ArrayList<FriendRequest> requestArrayList=new ArrayList<>();

    public NotificationPage() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        SharedPreferences sharedPreferences= getContext().getSharedPreferences("com.example.facebook", Context.MODE_PRIVATE);
        Retrofit retrofit= NotificationRetrofitBuilder.getInstance();
        IpostApi iPostApi=retrofit.create(IpostApi.class);

        Call<List<Notification>> responseCall=iPostApi.getNotification(sharedPreferences.getString("userId",""));
        responseCall.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                Log.e("notification",response.body().toString());
                notificationArrayList=response.body();
                NotificationRecyclerViewAdapter recyclerViewAdapter=new NotificationRecyclerViewAdapter(notificationArrayList);
                RecyclerView friendRv=view.findViewById(R.id.notification_rv);
                friendRv.setLayoutManager(new LinearLayoutManager(getContext()));
                friendRv.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Log.e("notification",t.getMessage());

            }
        });
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv=view.findViewById(R.id.friend_rv);

//        notificationArrayList.add(new notification_model("saptak chakroborty","2 minutes ago","https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png","Have a nice day "));
//        notificationArrayList.add(new notification_model("saptak chakroborty","19 minutes ago","https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png","Posted a new comment : looking beautiful "));
//        notificationArrayList.add(new notification_model("saptak chakroborty","43 minutes ago","https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png","Posted a new comment : nice try"));
//        notificationArrayList.add(new notification_model("saptak chakroborty","1 hr ago","https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png","commented on your dp"));
//        notificationArrayList.add(new notification_model("saptak chakroborty","1 hr ago","https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png","Posted a new comment : which place is this"));
//        notificationArrayList.add(new notification_model("saptak chakroborty","1 hr ago","https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png","have a nice day"));
//        notificationArrayList.add(new notification_model("saptak chakroborty","3 hr ago","https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png"," Adam posted a new video : should the bill be spent on mivies"));
//        notificationArrayList.add(new notification_model("saptak chakroborty","3 hr ago","https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png","commented on your dp"));
//        notificationArrayList.add(new notification_model("saptak chakroborty","4 hr ago","https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png","commented on your dp"));



        requestArrayList.add(new FriendRequest("Al khareef","57 mutual friends"));
        requestArrayList.add(new FriendRequest("Adam ","23 mutual friends"));
        requestArrayList.add(new FriendRequest("Paxton Parkor","11 mutual friends"));
        requestArrayList.add(new FriendRequest("Pritam Dey","42 mutual friends"));
        FriendRequestRecyclerViewAdapter adapter=new FriendRequestRecyclerViewAdapter(requestArrayList);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_page, container, false);
    }
}