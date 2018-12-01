package com.example.cln62.onlineshoppingapp.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.adapter.ProductListAdapter;
import com.example.cln62.onlineshoppingapp.adapter.RecyclerviewHomeAdapter;
import com.example.cln62.onlineshoppingapp.network.ImageLoader;
import com.example.cln62.onlineshoppingapp.pojo.CatSubcategory;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.ui.product.ProductFragment;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;

import java.util.List;

public class HomeFragment extends Fragment implements RecyclerviewHomeAdapter.OnItemClickListener,
        ProductListAdapter.ClickListener{

    public View myRoot;

    private ImageLoader imageLoader;
    String userId, apiKey;
    List<CatSubcategory> list;
    RecyclerviewHomeAdapter mAdapter;
    RecyclerView mRecyclerView;
    List<CatSubcategory> subCategory;
    List<Product> productList;
    MySharedPrefences mySharedPrefences;
    private ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (myRoot == null) {
            myRoot = inflater.inflate(R.layout.fragment_category, container, false);

        Bundle bundle = getArguments();
        mySharedPrefences = new MySharedPrefences();
/*        userId = bundle.getString("id");
        apiKey = bundle.getString("apikey");*/
        userId = mySharedPrefences.getId(getActivity());

        apiKey = mySharedPrefences.getApiKey(getActivity());
            initRecyclerView(myRoot);
        }
        return myRoot;
    }

    public void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview_home);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.setLayoutManager(layoutManager);

        setRetainInstance(true);

        progress = new ProgressDialog(getActivity());
        progress.setTitle("MAIN CAT LOADING");
        progress.setCancelable(false);
        progress.show();

//        imageLoader = new ImageLoader(getContext());
        imageLoader = new ImageLoader(getContext(), this);
        imageLoader.loadCategoryImage(userId, apiKey);



//        list = imageLoader.loadCategoryImage(userId, apiKey);
        /*mAdapter = new RecyclerviewHomeAdapter(list, getContext());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();*/
    }

/*    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        mListState=mRecyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, mListState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mBundleRecyclerViewState != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mListState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
                    recyclerView.getLayoutManager().onRestoreInstanceState(mListState);

                }
            }, 50);

        }

        recyclerView.setLayoutManager(gridLayoutManager);
    }*/


    @Override
    public void onItemClick(View view, int position) {
        String cid = list.get(position).getCid();
        Log.i("aaa3", cid);
        ((HomeActivity) getActivity()).categoryListened(cid);
    }

    @Override
    public void onItemClick(View view, int position, String cid) {
        String scid = subCategory.get(position).getCid(); //actually should be scid, because using the same pojo(CatSubcategory) for category
        // and subcategory, so the first attribute still called cid, but for subcategory it actually is scid
        Log.i("aaa3", cid);
        Log.i("aaa3", scid);
        ((HomeActivity) getActivity()).categoryListened(cid, scid);
    }

    public void resetCategory() {
        mRecyclerView.setAdapter(mAdapter);
    }

    public void showSubCategoryConfirm(List<CatSubcategory> list, String cid) {
        RecyclerviewHomeAdapter mAdapter = new RecyclerviewHomeAdapter(list, getContext());
        mAdapter.setOnItemClickListener(this, cid);
//        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview_home);

        mRecyclerView.setAdapter(mAdapter);
        subCategory = list;
    }

    @Override
    public void itemClicked(View view, int position) {
        Product product = productList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("id", product.getId());
        bundle.putString("pname", product.getPname());
        bundle.putString("quantity", product.getQuantity());
        bundle.putString("prize", product.getPrize());
        bundle.putString("description", product.getDescription());
        bundle.putString("image", product.getImage());

        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(
                R.id.content_home, productFragment, "ProductFrag").addToBackStack(null).commit();
    }

    public void showProductListConfirm(List<Product> resList) {
        ProductListAdapter mAdapter = new ProductListAdapter(getContext(), resList);
        mAdapter.setClickListener(this);

//        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_home);
        mRecyclerView.setAdapter(mAdapter);
        productList = resList;
    }

    public void initRecyclerViewFinished(List<CatSubcategory> resList) {

        progress.dismiss();

        list = resList;
        mAdapter = new RecyclerviewHomeAdapter(resList, getContext());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }
}
