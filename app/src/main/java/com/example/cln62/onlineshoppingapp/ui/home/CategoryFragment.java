package com.example.cln62.onlineshoppingapp.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.adapter.RecyclerviewCategoryAdapter;
import com.example.cln62.onlineshoppingapp.network.ImageLoader;
import com.example.cln62.onlineshoppingapp.pojo.Category;

import java.util.List;

public class CategoryFragment extends Fragment {

    private ImageLoader imageLoader;
    String userId, apiKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        Bundle bundle = getArguments();
        userId = bundle.getString("id");
        apiKey = bundle.getString("apikey");
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        imageLoader = new ImageLoader();
        Log.i("aaa3", userId);
        Log.i("aaa3", apiKey);
        List<Category> list = imageLoader.loadImage(userId, apiKey);
        RecyclerviewCategoryAdapter mAdapter = new RecyclerviewCategoryAdapter(list, getContext());
        mRecyclerView.setAdapter(mAdapter);

    }
}
