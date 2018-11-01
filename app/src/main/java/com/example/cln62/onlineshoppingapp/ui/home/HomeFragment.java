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
import com.example.cln62.onlineshoppingapp.adapter.ProductListAdapter;
import com.example.cln62.onlineshoppingapp.adapter.RecyclerviewCategoryAdapter;
import com.example.cln62.onlineshoppingapp.network.ImageLoader;
import com.example.cln62.onlineshoppingapp.pojo.Category;
import com.example.cln62.onlineshoppingapp.pojo.Product;

import java.util.List;

public class HomeFragment extends Fragment implements RecyclerviewCategoryAdapter.OnItemClickListener,
        ProductListAdapter.ClickListener{

    private ImageLoader imageLoader;
    String userId, apiKey;
    List<Category> list;
    RecyclerviewCategoryAdapter mAdapter;
    RecyclerView mRecyclerView;
    List<Category> subCategory;
    List<Product> productList;

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
        mRecyclerView = view.findViewById(R.id.recyclerview_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        imageLoader = new ImageLoader(getContext());

        list = imageLoader.loadCategoryImage(userId, apiKey);
        mAdapter = new RecyclerviewCategoryAdapter(list, getContext());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        String cid = list.get(position).getCid();
        Log.i("aaa3", cid);
        ((HomeActivity) getActivity()).categoryListened(cid);
    }

    @Override
    public void onItemClick(View view, int position, String cid) {
        String scid = subCategory.get(position).getCid(); //actually should be scid, because using the same pojo(Category) for category
        // and subcategory, so the first attribute still called cid, but for subcategory it actually is scid
        Log.i("aaa3", cid);
        Log.i("aaa3", scid);
        ((HomeActivity) getActivity()).categoryListened(cid, scid);
    }

    public void resetCategory() {
        mRecyclerView.setAdapter(mAdapter);
    }

    public void showSubCategoryConfirm(List<Category> list, String cid) {
        RecyclerviewCategoryAdapter mAdapter = new RecyclerviewCategoryAdapter(list, getContext());
        mAdapter.setOnItemClickListener(this, cid);
//        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview_home);

        mRecyclerView.setAdapter(mAdapter);
        subCategory = list;
    }

    @Override
    public void itemClicked(View view, int position) {
        Product product = productList.get(position);
        HomeContract.View homeContract = (HomeContract.View) getActivity();
        homeContract.dataTransferMethod(product);
    }

    public void showProductListConfirm(List<Product> resList) {
        ProductListAdapter mAdapter = new ProductListAdapter(getContext(), resList);
        mAdapter.setClickListener(this);

//        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_home);
        mRecyclerView.setAdapter(mAdapter);
        productList = resList;
    }
}
