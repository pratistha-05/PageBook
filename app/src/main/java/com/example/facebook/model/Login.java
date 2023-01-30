package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class Login implements Serializable {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("token")
    private String token;
    @SerializedName("socialMediaId")
    private int socialMediaId=1;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId() {
        this.socialMediaId = 1;
    }

    @Override
    public String toString() {
        return "LoginRequestDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", socialMediaId='" + socialMediaId + '\'' +
                '}';
    }
}


