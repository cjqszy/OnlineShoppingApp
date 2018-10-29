package com.example.cln62.onlineshoppingapp;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "homeactivity";
    private Toolbar mToolbar;
    private TextView tv_toolbarTitle, tv_username;
    private ImageButton ib_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {

        //toolbar
        mToolbar = findViewById(R.id.myToolbar);
        ib_cart = mToolbar.findViewById(R.id.ib_cart);
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
        getSupportFragmentManager().beginTransaction().replace(R.id.content_home, new CategoryFragment(), null).commit();

        /*RecyclerView mRecyclerView = findViewById(R.id.recyclerview_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter mAdapter = new RecyclerAdapter();


        mRecyclerView.setAdapter(mAdapter);*/

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }


}
