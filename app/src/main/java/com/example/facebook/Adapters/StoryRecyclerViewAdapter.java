package com.example.facebook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.facebook.R;
import com.example.facebook.model.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryRecyclerViewAdapter extends RecyclerView.Adapter<StoryRecyclerViewAdapter.ViewHolder> {
    List<Story> storyList=new ArrayList<>();
    private final UserInterface userInterface ;


    public StoryRecyclerViewAdapter(List<Story> storyList, UserInterface userInterface) {
        this.storyList=storyList;
        this.userInterface = userInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_item,parent,false);
        return new StoryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story=storyList.get(position);
        holder.storyName.setText(story.getUsername());
        Glide.with(holder.storyImg.getContext()).load(story.getUrl()).into(holder.storyImg);

    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }
    public interface UserInterface{
        void onUserClick( Story story,int position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView storyName;
        private final ImageView story;
        private final ImageView storyImg;
        private final View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storyName = itemView.findViewById(R.id.story_name);
            story=itemView.findViewById(R.id.story_dp);
            storyImg=itemView.findViewById(R.id.story);
            view = itemView;
            //assigning view root
        }
    }
}
