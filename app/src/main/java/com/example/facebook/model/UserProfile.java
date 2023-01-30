package com.example.facebook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserProfile {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("bio")
    private String bio;
    @SerializedName("profilePic")
    private String profilePic;
    @SerializedName("coverPic")
    private String coverPic;
    @SerializedName("accountType")
    private String accountType;
    @SerializedName("accountScope")
    private String accountScope="public";
    @SerializedName("location")
    private String location;
    @SerializedName("about")
    private String about;
    @SerializedName("phoneNo")
    private String phoneNo;

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", coverPic='" + coverPic + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountScope='" + accountScope + '\'' +
                ", location='" + location + '\'' +
                ", about='" + about + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", socialMediaId=" + socialMediaId +
                ", interest=" + interest +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountScope() {
        return accountScope;
    }

    public void setAccountScope(String accountScope) {
        this.accountScope = accountScope;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId(int socialMediaId) {
        this.socialMediaId = socialMediaId;
    }

    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    @SerializedName("gender")
    private String gender;
    @SerializedName("dob")
    private String dob;
    @SerializedName("socialMediaId")
    private int socialMediaId=1;
    @SerializedName("interest")
    private List<String> interest;
}
