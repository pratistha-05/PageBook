package com.example.facebook.UserAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.facebook.IpostApi;
import com.example.facebook.R;
import com.example.facebook.RetrofitBuilder.LoginRetrofitBuilder;
import com.example.facebook.model.UserData;

import retrofit2.Call;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signup=findViewById(R.id.btnSignup);
        TextView email=findViewById(R.id.email);
        TextView password=findViewById(R.id.password);
        TextView contact=findViewById(R.id.phone);
        signup.setOnClickListener(view -> {
            Intent intent=new Intent(SignupActivity.this,UserDetails.class);
            Bundle bundle = new Bundle();
            bundle.putString("Email", email.getText().toString());
            bundle.putString("PhoneNo", contact.getText().toString());
            bundle.putString("Password", password.getText().toString());
            intent.putExtras(bundle);

            startActivity(intent);
        });
    }
}