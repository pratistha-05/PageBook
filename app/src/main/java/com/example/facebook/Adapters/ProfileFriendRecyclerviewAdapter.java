package com.example.facebook.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.facebook.R;
import com.example.facebook.model.PostData;
import com.example.facebook.model.ProfileFriends;

import java.util.ArrayList;
import java.util.List;

public class ProfileFriendRecyclerviewAdapter extends RecyclerView.Adapter<ProfileFriendRecyclerviewAdapter.ViewHolder>{
    List<ProfileFriends> friendList=new ArrayList<>();

    public ProfileFriendRecyclerviewAdapter(List<ProfileFriends> friendList) {
        this.friendList=friendList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_on_profile,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfileFriends postData=friendList.get(position);
        holder.postName.setText(postData.getName());
        Glide.with(holder.postImg.getContext()).load(postData.getFriendImg()).placeholder(R.drawable.ic_launcher_background).into(holder.postImg);

    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView postName;
        private final ImageView postImg;


        private final View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postName = itemView.findViewById(R.id.profile_friend_name);
            postImg=itemView.findViewById(R.id.profile_friend_dp);
            view = itemView;
            //assigning view root
        }
    }
}
