package com.example.facebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.facebook.Notification.NotificationRetrofitBuilder;
import com.example.facebook.Notification.Response;
import com.example.facebook.fragments.FeedPage;
import com.example.facebook.fragments.NotificationPage;
import com.example.facebook.fragments.RecommendationPage;
import com.example.facebook.fragments.UserProfilePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        FeedPage fragment1 = new FeedPage();
        NotificationPage fragment2 = new NotificationPage();
        RecommendationPage fragment3 = new RecommendationPage();
        UserProfilePage fragment4 = new UserProfilePage();
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_menu);
        setCurrentFragment(fragment1);
        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.item1:
                    setCurrentFragment(fragment1);
                    break;
                case R.id.item2:
                    setCurrentFragment(fragment3);
                    break;
                case R.id.item3:
                    setCurrentFragment(fragment2);
                    break;
                case R.id.item4:
                    setCurrentFragment(fragment4);
                    break;

            }
            return false;
        });

        FirebaseMessaging.getInstance().subscribeToTopic("quiz")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Log.d("subscribe", msg);
                        Toast.makeText(HomePage.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.e("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        sendRegistrationToServer(token);
                        Log.d("firstToken", token);
                    }

                });

    }
    private void sendRegistrationToServer(String token){
        SharedPreferences sharedPreferences=getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        Retrofit retrofit = NotificationRetrofitBuilder.getInstance();
        IpostApi iPostApi = retrofit.create(IpostApi.class);
        Response response = new Response();
        response.setToken(token);
        response.setUserId(sharedPreferences.getString("userId",""));
        response.setPlatformId("3");
        response.setSocialMediaId(1);
        Call<Response> responseCall = iPostApi.sendToken(response);
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                System.out.println("Sucess");
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });

    }
    private void setCurrentFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();
    }

    }
