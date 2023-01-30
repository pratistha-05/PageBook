package com.example.facebook.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.facebook.IpostApi;
import com.example.facebook.R;
import com.example.facebook.RetrofitBuilder.PostRetrofitBuilder;
import com.example.facebook.model.CommentsAndLikes;
import com.example.facebook.model.NewPostModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    List<NewPostModel> postList=new ArrayList<>();
    private final UserInterface userInterface ;


    public RecyclerViewAdapter(List<NewPostModel> postList, UserInterface userInterface) {
        this.postList=postList;
        this.userInterface = userInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Retrofit retrofit = PostRetrofitBuilder.getInstance();
//        IpostApi ipostApi = retrofit.create(IpostApi.class);
//        Call<CommentsAndLikes> call = ipostApi.getReactions("78965");
//        call.enqueue(new Callback<CommentsAndLikes>() {
//                         @Override
//                         public void onResponse(Call<CommentsAndLikes> call, Response<CommentsAndLikes> response) {
//
//                         }
//
//                         @Override
//                         public void onFailure(Call<CommentsAndLikes> call, Throwable t) {
//
//                         }
//                     });


        NewPostModel postData = postList.get(position);
        Glide.with(holder.postImg.getContext()).load(postData.getPostUrl()).placeholder(R.drawable.ic_launcher_background).into(holder.postImg);
        holder.likeTv.setText("");
        String postId=postData.getId();
        holder.dislikeTv.setText("");

        holder.postDate.setText(postData.getDate());
        holder.postName.setText(postData.getUsername());
        holder.postContent.setText(postData.getCaption());
        holder.hashtag.setText(postData.getHashtag());
        holder.like.setOnClickListener(view ->  {

            holder.likeTv.setText("+1 like");
            userInterface.onLike(position,postId);
        });

        holder.dislike.setOnClickListener(view ->  {

            holder.dislikeTv.setText("+1 dislike");
            userInterface.onDislike(position,postId);
        });

        holder.postBtn.setOnClickListener(view ->  {
           String res=holder.commentEt.getText().toString();
           userInterface.onPostComment(res,postId);
        });



    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public interface UserInterface{
        void onUserClick( NewPostModel productData,int position);

        void onDislike(int position, String postId);

        void onLike(int position,String postId);

        void onPostComment(String res,String id);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView postName;
        private final TextView postDate;
        private final TextView hashtag;
        private final TextView postContent;
        private final EditText commentEt;
        private final ImageView postBtn;

        private final TextView dislikeTv;
        private final TextView likeTv;
        private final TextView shareTv;
        private final TextView commentTv;

        private final ImageView postImg;
        private final ImageView like;
        private final ImageView dislike;
        private final ImageView comment;

        private final ImageView share;



        private final View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postName = itemView.findViewById(R.id.post_name);
            postDate = itemView.findViewById(R.id.post_date);
            hashtag = itemView.findViewById(R.id.hashtag);
            postContent = itemView.findViewById(R.id.caption);
            commentEt = itemView.findViewById(R.id.new_comment);
            postBtn=itemView.findViewById(R.id.post_comment);
            dislikeTv = itemView.findViewById(R.id.dislike_txt);

            likeTv = itemView.findViewById(R.id.like_txt);
            commentTv = itemView.findViewById(R.id.comment_txt);
            shareTv = itemView.findViewById(R.id.share_txt);
            postImg=itemView.findViewById(R.id.post_image);
            like=itemView.findViewById(R.id.like);
            dislike=itemView.findViewById(R.id.dislike);
            comment=itemView.findViewById(R.id.comment);
            share=itemView.findViewById(R.id.share);

            view = itemView;
            //assigning view root
        }
    }
}
