package com.example.facebook.UserAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.facebook.HomePage;
import com.example.facebook.IpostApi;
import com.example.facebook.R;
import com.example.facebook.RetrofitBuilder.LoginRetrofitBuilder;
import com.example.facebook.RetrofitBuilder.SignUpRetrofitBuilder;
import com.example.facebook.model.Login;
import com.example.facebook.model.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView email = findViewById(R.id.emailInput);
        TextView password = findViewById(R.id.passwordInput);
        Button signIn=findViewById(R.id.btnSignup);
        Button loginButton = findViewById(R.id.login);
        Retrofit retrofit = LoginRetrofitBuilder.getInstance();
        IpostApi ipostApi = retrofit.create(IpostApi.class);
        signIn.setOnClickListener(view -> {
            Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(intent);
        });
        loginButton.setOnClickListener(view -> {
            String emailInput = email.getText().toString();
            String passwordInput = password.getText().toString();
            Login userData=new Login();
            userData.setEmail(emailInput);
            userData.setPassword(passwordInput);
            Call<Login> userDataCall = ipostApi.logIn(userData);
            Log.e("login object",userData.toString());
            userDataCall.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    Log.e("Login response", String.valueOf(response.body()));

                    SharedPreferences sharedPreferences=getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putString("userId",response.body().getEmail());
                    Retrofit retrofit = SignUpRetrofitBuilder.getInstance();
                    IpostApi ipostApi = retrofit.create(IpostApi.class);

                    Call<UserData> userDataCall = ipostApi.getCred(response.body().getEmail());
                    userDataCall.enqueue(new Callback<UserData>() {
                        @Override
                        public void onResponse(Call<UserData> call, Response<UserData> response) {
                            Log.e("response in email",response.body().toString());
                            editor.putString("id",response.body().getId());
                            editor.putString("username",response.body().getName());
                        }

                        @Override
                        public void onFailure(Call<UserData> call, Throwable t) {
                            Log.e("error in email",t.getMessage());

                        }
                    });

                    Intent i=new Intent(LoginActivity.this, HomePage.class);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Log.e("Login error",t.getMessage());

                }
            });

        });
    }
}