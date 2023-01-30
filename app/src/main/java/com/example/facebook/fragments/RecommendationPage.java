package com.example.facebook.fragments;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.facebook.Adapters.RecyclerViewAdapter;
import com.example.facebook.Adapters.SearchRecyclerViewAdapter;
import com.example.facebook.IpostApi;
import com.example.facebook.R;
import com.example.facebook.RetrofitBuilder.SignUpRetrofitBuilder;
import com.example.facebook.model.SearchData;
import com.example.facebook.model.UserData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecommendationPage extends Fragment {

    public RecommendationPage() {
        // Required empty public constructor
    }
    List<SearchData> searchDataList=new ArrayList<>();

    List<SearchData> newsearchDataList=new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit retrofit= SignUpRetrofitBuilder.getInstance();
        IpostApi iPostApi=retrofit.create(IpostApi.class);
        RecyclerView rv=view.findViewById(R.id.search_rv);

        Call<List<SearchData>> userDataCall = iPostApi.getUsers();
        userDataCall.enqueue(new Callback<List<SearchData>>() {
            @Override
            public void onResponse(Call<List<SearchData>> call, Response<List<SearchData>> response) {
                searchDataList=response.body();
                SearchRecyclerViewAdapter recyclerViewAdapter1 = new SearchRecyclerViewAdapter(searchDataList);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(recyclerViewAdapter1);

            }

            @Override
            public void onFailure(Call<List<SearchData>> call, Throwable t) {
            }
        });
        SearchView searchInput = view.findViewById(R.id.searchField);

        searchInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {
                Retrofit retrofit= SignUpRetrofitBuilder.getInstance();
                IpostApi iPostApi=retrofit.create(IpostApi.class);
                RecyclerView rv=view.findViewById(R.id.search_rv);
                Call<List<SearchData>> userDataCall = iPostApi.getUsers();
                userDataCall.enqueue(new Callback<List<SearchData>>() {
                    @Override
                    public void onResponse(Call<List<SearchData>> call, Response<List<SearchData>> response) {
                        for(int i=0;i<searchDataList.size();i++)
                        {
                            if(searchDataList.get(i).getName().toLowerCase().equals(s))
                                newsearchDataList.add(searchDataList.get(i));
                        }
                        SearchRecyclerViewAdapter recyclerViewAdapter1 = new SearchRecyclerViewAdapter(newsearchDataList);
                        rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        rv.setAdapter(recyclerViewAdapter1);

                    }

                    @Override
                    public void onFailure(Call<List<SearchData>> call, Throwable t) {
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
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
        return inflater.inflate(R.layout.fragment_recommendation_page, container, false);
    }
}