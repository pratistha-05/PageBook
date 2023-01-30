package com.example.facebook.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.facebook.Adapters.RecyclerViewAdapter;
import com.example.facebook.Adapters.StoryRecyclerViewAdapter;
import com.example.facebook.IpostApi;
import com.example.facebook.NewPost;
import com.example.facebook.R;
import com.example.facebook.RetrofitBuilder.CommentRetrofitBuilder;
import com.example.facebook.RetrofitBuilder.PostRetrofitBuilder;
import com.example.facebook.RetrofitBuilder.ReactionRetrofitBuilder;
import com.example.facebook.RetrofitBuilder.StoryRetrofitBuilder;
import com.example.facebook.model.Comment;
import com.example.facebook.model.CommentsAndLikes;
import com.example.facebook.model.Like;
import com.example.facebook.model.NewPostModel;
import com.example.facebook.model.Story;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FeedPage extends Fragment implements StoryRecyclerViewAdapter.UserInterface,RecyclerViewAdapter.UserInterface{
    //SharedPreferences sharedPreferences= getActivity().getSharedPreferences("com.example.facebook", Context.MODE_PRIVATE);
    private static final int PICK_IMAGE_REQUEST = 22 ;
    List<NewPostModel> postList=new ArrayList<>();
    List<Story> storyList=new ArrayList<>();
    ImageView story;
    RecyclerView storyRv;
    private Uri downloadUri;
    private Uri imageUri;
    public FeedPage() {
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText newPost=view.findViewById(R.id.new_post);
        ImageView addStory=view.findViewById(R.id.add_story);
        story=view.findViewById(R.id.story_dp);

        Retrofit retrofit = StoryRetrofitBuilder.getInstance();
        IpostApi ipostApi = retrofit.create(IpostApi.class);
        Call<List<Story>> userDataCall = ipostApi.fetchStories();
        userDataCall.enqueue(new Callback<List<Story>>() {
                                 @Override
                                 public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                                    storyList=response.body();
                                     storyRv=view.findViewById(R.id.story_rv);
                                     StoryRecyclerViewAdapter recyclerViewAdapter = new StoryRecyclerViewAdapter(storyList, FeedPage.this);
                                     storyRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                                     storyRv.setAdapter(recyclerViewAdapter);
                                 }

                                 @Override
                                 public void onFailure(Call<List<Story>> call, Throwable t) {
                                     Log.e("error stories",t.getMessage());

                                 }
                             });



        RecyclerView postRv=view.findViewById(R.id.post_rv);
        Retrofit retrofit1 = PostRetrofitBuilder.getInstance();
        IpostApi ipostApi1 = retrofit1.create(IpostApi.class);
        Call<List<NewPostModel>> userDataCall1 = ipostApi1.fetchPosts();
        userDataCall1.enqueue(new Callback<List<NewPostModel>>() {
            @Override
            public void onResponse(Call<List<NewPostModel>> call, Response<List<NewPostModel>> response) {
                Log.e("post response",response.body().toString());
                postList=response.body();

                RecyclerViewAdapter recyclerViewAdapter1 = new RecyclerViewAdapter(postList, FeedPage.this);
                postRv.setLayoutManager(new LinearLayoutManager(getContext()));
                postRv.setAdapter(recyclerViewAdapter1);


            }

            @Override
            public void onFailure(Call<List<NewPostModel>> call, Throwable t) {

            }
        });




        addStory.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
        });

       newPost.setOnTouchListener((view1, motionEvent) -> {
           Intent intent=new Intent(getContext(), NewPost.class);
           startActivity(intent);
           return false;
       });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 && resultCode == -1 && data != null && data.getData() != null) {
            imageUri = data.getData();
            //Log.e("image", String.valueOf(imageUri));
            story.setImageURI(imageUri);

            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageRef = storage.getReference();
            StorageReference Ref = storageRef.child("pagebook/"+imageUri.getLastPathSegment());
            UploadTask uploadTask=Ref.putFile(imageUri);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                }
            });

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return Ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        downloadUri = task.getResult();
                        Log.e("story uri",downloadUri.toString());
                        SharedPreferences sharedPreferences= getContext().getSharedPreferences("com.example.facebook", Context.MODE_PRIVATE);
                        Date c = Calendar.getInstance().getTime();
                        @SuppressLint
                                ("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
                        Story story=new Story();
                        story.setUrl(downloadUri.toString());
                        story.setCreatedTime(dateFormat.format(date));
                        story.setId(sharedPreferences.getString("id",""));
                        story.setUsername(sharedPreferences.getString("username",""));
                        Log.e("story Posted",story.toString());
                        Retrofit retrofit = StoryRetrofitBuilder.getInstance();
                        IpostApi ipostApi = retrofit.create(IpostApi.class);
                        Call<Story> userDataCall = ipostApi.postStories(story);
                        userDataCall.enqueue(new Callback<Story>() {
                            @Override
                            public void onResponse(Call<Story> call, Response<Story> response) {

                                Log.e("response in story",response.body().toString());

                            }

                            @Override
                            public void onFailure(Call<Story> call, Throwable t) {
                                Log.e("error stories",t.getMessage());

                            }
                        });
                    } else {
                    }
                }
            });
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_page, container, false);
    }

    @Override
    public void onUserClick(Story story, int position) {

    }

    @Override
    public void onUserClick(NewPostModel productData, int position) {

    }

    @Override
    public void onDislike(int position, String id) {
        Retrofit retrofit = ReactionRetrofitBuilder.getInstance();
        IpostApi ipostApi = retrofit.create(IpostApi.class);
        Like like=new Like();
        //TODO
        //like.setId();
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("com.example.facebook", Context.MODE_PRIVATE);
        like.setUserId(sharedPreferences.getString("id",""));
        like.setStatus("dislike");
        like.setId(id);
        Call<CommentsAndLikes> userDataCall = ipostApi.addDislike(like);
        userDataCall.enqueue(new Callback<CommentsAndLikes>() {
            @Override
            public void onResponse(Call<CommentsAndLikes> call, Response<CommentsAndLikes> response) {
                Log.e("response in like", String.valueOf(response.body()));

            }

            @Override
            public void onFailure(Call<CommentsAndLikes> call, Throwable t) {
                Log.e("error in like", String.valueOf(t.getMessage()));

            }
        });
    }


    @Override
    public void onLike(int position,String id) {
        Retrofit retrofit = ReactionRetrofitBuilder.getInstance();
        IpostApi ipostApi = retrofit.create(IpostApi.class);
        Like like=new Like();
        //TODO
        //like.setId();
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("com.example.facebook", Context.MODE_PRIVATE);
        like.setUserId(sharedPreferences.getString("id",""));
        like.setStatus("like");
        like.setId(id);
        Call<CommentsAndLikes> userDataCall = ipostApi.addLike(like);
        userDataCall.enqueue(new Callback<CommentsAndLikes>() {
            @Override
            public void onResponse(Call<CommentsAndLikes> call, Response<CommentsAndLikes> response) {
                Log.e("response in like", String.valueOf(response.body()));

            }

            @Override
            public void onFailure(Call<CommentsAndLikes> call, Throwable t) {
                Log.e("error in like", String.valueOf(t.getMessage()));

            }
        });
    }

    @Override
    public void onPostComment(String res,String id) {
        Retrofit retrofit = CommentRetrofitBuilder.getInstance();
        IpostApi ipostApi = retrofit.create(IpostApi.class);
        Comment comment=new Comment();
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("com.example.facebook", Context.MODE_PRIVATE);
        comment.setCommentBy(sharedPreferences.getString("username",""));
        comment.setPostId(id);
       comment.setText(res);

        Call<CommentsAndLikes> userDataCall = ipostApi.comment(comment);
        userDataCall.enqueue(new Callback<CommentsAndLikes>() {
            @Override
            public void onResponse(Call<CommentsAndLikes> call, Response<CommentsAndLikes> response) {
                Log.e("response in comment", String.valueOf(response.body()));
                Toast.makeText(getContext(), "Comment is added", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CommentsAndLikes> call, Throwable t) {
                Log.e("error in comment", String.valueOf(t.getMessage()));

            }
        });
    }
}