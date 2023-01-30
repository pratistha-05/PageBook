package com.example.facebook.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebook.R;
import com.example.facebook.model.FriendRequest;

import java.util.ArrayList;

public class FriendRequestRecyclerViewAdapter extends RecyclerView.Adapter<FriendRequestRecyclerViewAdapter.ViewHolder> {
ArrayList<FriendRequest> friendRequestArrayList=new ArrayList<>();

    public FriendRequestRecyclerViewAdapter(ArrayList<FriendRequest> requestArrayList) {
        this.friendRequestArrayList=requestArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.request_item,parent,false);
        return new FriendRequestRecyclerViewAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendRequest request=friendRequestArrayList.get(position);
        holder.name.setText(request.getPostername());
        holder.mutuals.setText(request.getMutuals());
    }

    @Override
    public int getItemCount() {
        return friendRequestArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView mutuals;
        private final View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.request_name);
            mutuals = itemView.findViewById(R.id.request_mutuals);
            view = itemView;
            //assigning view root
        }
    }
}
