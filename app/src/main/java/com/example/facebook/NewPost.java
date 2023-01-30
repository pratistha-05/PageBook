package com.example.facebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.facebook.RetrofitBuilder.LoginRetrofitBuilder;
import com.example.facebook.RetrofitBuilder.PostRetrofitBuilder;
import com.example.facebook.model.NewPostModel;
import com.example.facebook.model.UserData;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewPost extends AppCompatActivity {
ImageView postImg;
private Uri imageUri;
private Uri downloadUri;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 && resultCode == -1 && data != null && data.getData() != null) {
            imageUri = data.getData();
            postImg.setImageURI(imageUri);

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

                    } else {
                    }
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        Button addPhoto=findViewById(R.id.add_Pic);
        Button addPost=findViewById(R.id.newPost);
        EditText postContent=findViewById(R.id.post_content);
        EditText postHashatg=findViewById(R.id.post_hashtag);

        postImg=findViewById(R.id.postImage);
        addPhoto.setOnClickListener(view -> {
            Intent intent=new Intent(Intent.ACTION_PICK);
//            intent.setType("image/*");
//            String[] mimeTypes = {"image/jpeg", "image/png"};
//            intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,22);

        });
        addPost.setOnClickListener(view -> {
            Log.e("clicked","button");
            Retrofit retrofit = PostRetrofitBuilder.getInstance();
            IpostApi ipostApi = retrofit.create(IpostApi.class);

            Date c = Calendar.getInstance().getTime();

            @SuppressLint
                    ("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();

            String post = postContent.getText().toString();
            String hashtag=postHashatg.getText().toString();
            SharedPreferences sharedPreferences= getApplication().getSharedPreferences("com.example.facebook", Context.MODE_PRIVATE);

            NewPostModel newPost=new NewPostModel();
                newPost.setUsername(sharedPreferences.getString("username",""));
                newPost.setPostUrl(downloadUri.toString());
                newPost.setHashtag(hashtag);
                newPost.setCaption(post);
                newPost.setUserId(sharedPreferences.getString("id",""));
                newPost.setDate(dateFormat.format(date));
                Log.e("new post",newPost.toString());
                Call<NewPostModel> userDataCall = ipostApi.newPost(newPost);
                userDataCall.enqueue(new Callback<NewPostModel>() {
                    @Override
                    public void onResponse(Call<NewPostModel> call, Response<NewPostModel> response) {
                        Log.e("response in new post",response.body().toString());
                        Toast.makeText(NewPost.this, "New Picture Posted", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(NewPost.this,HomePage.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<NewPostModel> call, Throwable t) {
                        Log.e("error in new post",t.getMessage());

                    }
                });
        });
    }
}