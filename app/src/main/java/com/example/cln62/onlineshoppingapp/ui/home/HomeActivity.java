package com.example.cln62.onlineshoppingapp.ui.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.adapter.RecyclerviewCategoryAdapter;
import com.example.cln62.onlineshoppingapp.network.ImageLoader;
import com.example.cln62.onlineshoppingapp.pojo.Category;
import com.example.cln62.onlineshoppingapp.pojo.SubCategory;
import com.example.cln62.onlineshoppingapp.ui.checkout.CheckOutActivity;

import java.util.List;


public class HomeActivity extends AppCompatActivity implements HomeContract.View,
        NavigationView.OnNavigationItemSelectedListener {

    HomePresenter homePresenter;
    private static final String TAG = "homeactivity";
    private Toolbar mToolbar;
    private TextView tv_toolbarTitle, tv_username;
    private ImageButton imageButtonCart;
    String userId, apiKey;
    private ImageLoader imageLoader;
    CategoryFragment categoryFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userId = getIntent().getStringExtra("id");
        apiKey = getIntent().getStringExtra("apikey");
        Log.i("aaa2", userId);

        homePresenter = new HomePresenter(this);
        homePresenter.initView();

        imageButtonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.cartCllicked();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void proceedCheckOut() {
        Intent i = new Intent(this, CheckOutActivity.class);
        startActivity(i);
    }

    @Override
    public void initViewConfirm() {
        //toolbar
        mToolbar = findViewById(R.id.myToolbar);
        imageButtonCart = mToolbar.findViewById(R.id.ib_cart);
        setSupportActionBar(mToolbar);

        //drawer
        DrawerLayout drawer = findViewById(R.id.drawer_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //recycler view
        categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", userId);
        bundle.putString("apikey", apiKey);
        categoryFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_home, categoryFragment, null).commit();

/*        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageLoader = new ImageLoader();
        List<Category> list = imageLoader.loadCategoryImage();
        RecyclerviewCategoryAdapter mAdapter = new RecyclerviewCategoryAdapter(list, this);
        mRecyclerView.setAdapter(mAdapter);*/
    }

    @Override
    public void categoryClickedConfirmed(String subId) {
        imageLoader = new ImageLoader(this);
        imageLoader.loadSubCategoryImage(userId, apiKey, subId);
    }

    @Override
    public void showSubCategory(List<Category> list) {
        RecyclerviewCategoryAdapter mAdapter = new RecyclerviewCategoryAdapter(list, this);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_home);

        mRecyclerView.setAdapter(mAdapter);
    }

    public void categoryListened(String subId) {
        homePresenter.categoryClicked(subId);
        Log.i("aaa4", subId);
    }

    @Override
    public void onBackPressed() {
        homePresenter.comeBack();
    }

    @Override
    public void comBackConfirm() {
        categoryFragment.resetCategory();
    }
}
