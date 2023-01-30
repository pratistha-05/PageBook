package com.example.facebook.UserAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facebook.HomePage;
import com.example.facebook.IpostApi;
import com.example.facebook.R;
import com.example.facebook.RetrofitBuilder.LoginRetrofitBuilder;
import com.example.facebook.RetrofitBuilder.SignUpRetrofitBuilder;
import com.example.facebook.model.UserData;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserDetails extends AppCompatActivity {
    private RadioButton radioSexButton;
    private RadioButton accountButton;
    List<String> interest=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.facebook.R.layout.activity_user_details);
        Button datePicker=findViewById(R.id.btPickDate);
        TextView dob=findViewById(R.id.tv_dob);
        TextView name=findViewById(R.id.nameInput);

        CheckBox checkBoxGaming = findViewById(R.id.gaming);
        CheckBox checkBoxTechnology = findViewById(R.id.tech);
        CheckBox checkBoxFood = findViewById(R.id.food);
        CheckBox checkBoxMusic = findViewById(R.id.music);
        CheckBox checkBoxSports = findViewById(R.id.sports);

        Button signUp=findViewById(R.id.signUp);
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("Email");
        String password = bundle.getString("Password");
        String contact = bundle.getString("PhoneNo");

        RadioGroup genderGroup=findViewById(R.id.gender);
        RadioGroup accountGroup=findViewById(R.id.radioAccount);

        datePicker.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(UserDetails.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            //secondSignUpDTO.setDOB(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });


        signUp.setOnClickListener(view -> {
            int selectedgender = genderGroup.getCheckedRadioButtonId();
            radioSexButton = (RadioButton) findViewById(selectedgender);
            int selectedAccount = accountGroup.getCheckedRadioButtonId();
            accountButton = (RadioButton) findViewById(selectedAccount);

            if(checkBoxGaming.isChecked()){
                interest.add("Gaming");
            }

            if(checkBoxTechnology.isChecked()){
                interest.add("Technology");
            }
            if(checkBoxFood.isChecked()){
                interest.add("Food");
            }
            if(checkBoxMusic.isChecked()){
                interest.add("Music");
            }
            if(checkBoxSports.isChecked()){
                interest.add("Sports");
            }
            //Toast.makeText(this, radioSexButton.getText(), Toast.LENGTH_SHORT).show();
            Retrofit retrofit = SignUpRetrofitBuilder.getInstance();
            IpostApi ipostApi = retrofit.create(IpostApi.class);
            UserData userData = new UserData();
            userData.setName(name.getText().toString());
            userData.setEmail(email);
            userData.setPassword(password);
            userData.setPhoneNo(contact);
            userData.setDOB(dob.getText().toString());
            userData.setGender(radioSexButton.getText().toString());
            userData.setInterest(interest);
            userData.setAccountType(accountButton.getText().toString());
            Call<UserData> userDataCall = ipostApi.signUp(userData);
            userDataCall.enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(Call<UserData> call, Response<UserData> response) {
                    Log.e("signup",response.body().toString());
                    SharedPreferences sharedPreferences=getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putString("id",response.body().getId());
                    editor.putString("username",response.body().getName());

                    editor.apply();
                    //Log.e("user details",response.body().toString());
                    Intent intent=new Intent(UserDetails.this, HomePage.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<UserData> call, Throwable t) {
                        Log.e("error in signup",t.getMessage());
                }
            });
        });
    }
}
