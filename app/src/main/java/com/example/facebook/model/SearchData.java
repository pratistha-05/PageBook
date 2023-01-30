package com.example.facebook.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchData{

	@SerializedName("gender")
	private String gender;

	@SerializedName("profilePic")
	private String profilePic;

	@SerializedName("accountType")
	private String accountType;

	@Override
	public String toString() {
		return "SearchData{" +
				"gender='" + gender + '\'' +
				", profilePic=" + profilePic +
				", accountType='" + accountType + '\'' +
				", about=" + about +
				", bio=" + bio +
				", phoneNo='" + phoneNo + '\'' +
				", interest=" + interest +
				", dob='" + dob + '\'' +
				", name='" + name + '\'' +
				", socialMediaId=" + socialMediaId +
				", accountScope='" + accountScope + '\'' +
				", location=" + location +
				", id='" + id + '\'' +
				", coverPic=" + coverPic +
				", email='" + email + '\'' +
				'}';
	}

	@SerializedName("about")
	private String about;

	@SerializedName("bio")
	private String bio;

	@SerializedName("phoneNo")
	private String phoneNo;

	@SerializedName("interest")
	private List<String> interest;

	@SerializedName("dob")
	private String dob;

	@SerializedName("name")
	private String name;

	@SerializedName("socialMediaId")
	private int socialMediaId=1;

	@SerializedName("accountScope")
	private String accountScope;

	@SerializedName("location")
	private String  location;

	@SerializedName("id")
	private String id;

	@SerializedName("coverPic")
	private String coverPic;

	@SerializedName("email")
	private String email;

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setProfilePic(String profilePic){
		this.profilePic = profilePic;
	}

	public String getProfilePic(){
		return profilePic;
	}

	public void setAccountType(String accountType){
		this.accountType = accountType;
	}

	public String getAccountType(){
		return accountType;
	}

	public void setAbout(String about){
		this.about = about;
	}

	public String getAbout(){
		return about;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	public String getBio(){
		return bio;
	}

	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}

	public String getPhoneNo(){
		return phoneNo;
	}

	public void setInterest(List<String> interest){
		this.interest = interest;
	}

	public List<String> getInterest(){
		return interest;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setSocialMediaId(int socialMediaId){
		this.socialMediaId = socialMediaId;
	}

	public int getSocialMediaId(){
		return socialMediaId;
	}

	public void setAccountScope(String accountScope){
		this.accountScope = accountScope;
	}

	public String getAccountScope(){
		return accountScope;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCoverPic(String coverPic){
		this.coverPic = coverPic;
	}

	public String getCoverPic(){
		return coverPic;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

}