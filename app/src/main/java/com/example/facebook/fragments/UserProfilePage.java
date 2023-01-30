package com.example.facebook.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facebook.Adapters.ProfileFriendRecyclerviewAdapter;
import com.example.facebook.Adapters.RecyclerViewAdapter;
import com.example.facebook.IpostApi;
import com.example.facebook.Notification.NotificationRetrofitBuilder;
import com.example.facebook.R;
import com.example.facebook.RetrofitBuilder.SignUpRetrofitBuilder;
import com.example.facebook.UserAuth.LoginActivity;
import com.example.facebook.model.PostData;
import com.example.facebook.model.ProfileFriends;
import com.example.facebook.model.UserData;
import com.example.facebook.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserProfilePage extends Fragment {
    List<ProfileFriends> friendList=new ArrayList<>();


    public UserProfilePage() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv=view.findViewById(R.id.profile_friends);
        ImageView editIcon=view.findViewById(R.id.edit);

        friendList.add(new ProfileFriends("Himani","https://images.unsplash.com/photo-1484591974057-265bb767ef71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80"));
        friendList.add(new ProfileFriends("John","https://iso.500px.com/wp-content/uploads/2014/07/big-one.jpg"));
        friendList.add(new ProfileFriends("Albert","https://i.pinimg.com/736x/de/97/d2/de97d2b820fa30e303f78c89b9dfef68.jpg"));
        friendList.add(new ProfileFriends("Preeti","https://llandscapes-10674.kxcdn.com/wp-content/uploads/2019/07/lighting.jpg"));
        friendList.add(new ProfileFriends("Karan","https://rioslandscapingtree.files.wordpress.com/2021/09/114310.jpg"));
        ProfileFriendRecyclerviewAdapter recyclerViewAdapter1 = new ProfileFriendRecyclerviewAdapter(friendList);
        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rv.setAdapter(recyclerViewAdapter1);
        TextView profileBio=view.findViewById(R.id.profile_bio);
        TextView name=view.findViewById(R.id.profile_name);
        TextView location=view.findViewById(R.id.location);
        TextView dob=view.findViewById(R.id.dob);
        Button lagOut=view.findViewById(R.id.logOut);
        TextView interests=view.findViewById(R.id.profile_interests);
        UserProfile userData=new UserProfile();
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("com.example.facebook", Context.MODE_PRIVATE);
        Retrofit retrofit= SignUpRetrofitBuilder.getInstance();
        IpostApi iPostApi=retrofit.create(IpostApi.class);

        Call<UserProfile> userDataCall = iPostApi.getProfile(sharedPreferences.getString("id",""));
        userDataCall.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                    Log.e("response in profile", String.valueOf(response.body()));
                    name.setText(response.body().getName());
                    dob.setText(response.body().getDob());
                    profileBio.setText(response.body().getBio());
                    String res="";
                    for(int i=0;i<response.body().getInterest().size();i++)
                    {
                        res+= response.body().getInterest().get(i)+" ";
                    }
                    interests.setText(res);
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.e("error in profile", String.valueOf(t.getMessage()));

            }
        });
        editIcon.setOnClickListener(view1 -> {
            final Dialog editProfile = new Dialog(getContext());
//                postMedia.setTitle("Post");
            editProfile.setContentView(R.layout.edit_profile);
            ImageView close = (ImageView) editProfile.findViewById(R.id.close);
            Button saveProfile = editProfile.findViewById(R.id.save_profile);
            saveProfile.setOnClickListener(view2 -> {
                TextView editName=editProfile.findViewById(R.id.edit_name);
                TextView editBio=editProfile.findViewById(R.id.edit_bio);
                TextView editAbout=editProfile.findViewById(R.id.edit_about);
                TextView editContact=editProfile.findViewById(R.id.edit_contact);
                UserProfile userProfile=new UserProfile();
                userProfile.setId(sharedPreferences.getString("id",""));
                userProfile.setName(editName.getText().toString());
                userProfile.setBio(editBio.getText().toString());
                userProfile.setAbout(editAbout.getText().toString());
                userProfile.setPhoneNo(editContact.getText().toString());
                Retrofit retrofit1= SignUpRetrofitBuilder.getInstance();
                IpostApi iPostApi1=retrofit1.create(IpostApi.class);
                Call<UserProfile> userDataCall1 = iPostApi1.updateProfile(userProfile);
                userDataCall1.enqueue(new Callback<UserProfile>() {
                    @Override
                    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                        Log.e("response in edit",response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<UserProfile> call, Throwable t) {
                        Log.e("error in edit",t.getMessage());

                    }
                });
            });

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editProfile.cancel();
                    // editProfile.closeOptionsMenu();
                }
            });
            editProfile.show();

            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(editProfile.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            editProfile.getWindow().setAttributes(layoutParams);

        });

        lagOut.setOnClickListener(view1 -> {
            SharedPreferences myPrefs = getActivity().getSharedPreferences("Activity",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.clear();
            editor.commit();
            //AppState.getSingleInstance().setLoggingOut(true);
            Toast.makeText(getContext(), "Successfully LoggedOut", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }
}