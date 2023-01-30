package com.example.facebook.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebook.R;
import com.example.facebook.model.NewPostModel;
import com.example.facebook.model.SearchData;

import java.util.ArrayList;
import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {
    List<SearchData> searchDataList=new ArrayList<>();

    public SearchRecyclerViewAdapter(List<SearchData> searchDataList) {
        this.searchDataList=searchDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchData data=searchDataList.get(position);
        holder.name.setText(data.getName());
        holder.gender.setText(data.getGender());
        holder.bio.setText(data.getBio());
        holder.account.setText(data.getAccountScope());



    }

    @Override
    public int getItemCount() {
        return searchDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView gender;
        private final TextView bio;
        private final TextView account;
        //private final ImageView dp;
        private final View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.search_name);
            gender = itemView.findViewById(R.id.search_gender);
            bio = itemView.findViewById(R.id.bio);
            account = itemView.findViewById(R.id.account);
            //dp = itemView.findViewById(R.id.notification_dp);
            view = itemView;
        }
    }
}
