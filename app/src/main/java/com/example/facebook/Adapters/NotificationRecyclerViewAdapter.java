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
import com.example.facebook.model.Notification;
import com.example.facebook.model.PostData;
import com.example.facebook.model.notification_model;

import java.util.ArrayList;
import java.util.List;

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder> {
    List<Notification> notificationArrayList=new ArrayList<>();

    public NotificationRecyclerViewAdapter(List<com.example.facebook.model.Notification> notificationArrayList) {
        this.notificationArrayList=notificationArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //PostData postData=postList.get(position);
        Notification notificationData=notificationArrayList.get(position);
        holder.content.setText(notificationData.getContent());

    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView content;
        private final View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.notification);
            view = itemView;
            //assigning view root
        }
    }
}
