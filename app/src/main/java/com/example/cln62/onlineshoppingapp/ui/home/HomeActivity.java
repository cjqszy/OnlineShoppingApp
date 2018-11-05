package com.example.cln62.onlineshoppingapp.ui.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.network.ImageLoader;
import com.example.cln62.onlineshoppingapp.pojo.CatSubcategory;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.ui.checkout.CartActivity;
import com.example.cln62.onlineshoppingapp.ui.history.OrderHistoryFragment;
import com.example.cln62.onlineshoppingapp.ui.login.LoginActivity;
import com.example.cln62.onlineshoppingapp.ui.profile.ProfileActivity;

import java.util.List;


public class HomeActivity extends AppCompatActivity implements HomeContract.View,
        NavigationView.OnNavigationItemSelectedListener {

    HomePresenter homePresenter;
    private static final String TAG = "homeactivity";
    private Toolbar mToolbar;
    private TextView tv_toolbarTitle, tv_username;
    private ImageButton imageButtonCart;
    String userId, apiKey, name, email;
    private ImageLoader imageLoader;
    HomeFragment homeFragment;
    NavigationView navigationView;
    TextView textViewName, textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userId = getIntent().getStringExtra("id");
        apiKey = getIntent().getStringExtra("apikey");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        navigationView = findViewById(R.id.nav_view);
/*        textViewName = navigationView.findViewById(R.id.textview_name);
        textViewEmail = navigationView.findViewById(R.id.textview_email);*/

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
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
//            homeFragment = new HomeFragment();
/*            Bundle bundle = new Bundle();
            bundle.putString("id", userId);
            bundle.putString("apikey", apiKey);
            homeFragment.setArguments(bundle);*/
//            homeFragment.initRecyclerView(homeFragment.getView());
           getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_home, homeFragment, "HomeFrag").addToBackStack(null).commit();
            onBackPressed();
        }
        else if (id == R.id.nav_profile) {
            Intent homeIntent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(homeIntent);
        } else if (id == R.id.nav_orders) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_home, new OrderHistoryFragment(), null).addToBackStack(null).commit();
            Log.i(TAG, "1");
        } else if (id == R.id.nav_topsellers) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_home, new TopSellerFragment()).
                    addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_service) {

        }  else if (id == R.id.nav_logout) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
/*            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);*/
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void proceedCart() {
        Intent i = new Intent(this, CartActivity.class);
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
        View head = navigationView.getHeaderView(0);
        textViewName = head.findViewById(R.id.textview_name);
        textViewEmail = head.findViewById(R.id.textview_email);
        textViewName.setText(name);
        textViewEmail.setText(email);

        //recycler view
        homeFragment = new HomeFragment();
/*        Bundle bundle = new Bundle();
        bundle.putString("id", userId);
        bundle.putString("apikey", apiKey);
        homeFragment.setArguments(bundle);*/
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_home, homeFragment, "HomeFrag").commit();

    }

    @Override
    public void showSubCategory(List<CatSubcategory> list, String cid) {
/*        RecyclerviewHomeAdapter mAdapter = new RecyclerviewHomeAdapter(list, this);
        mAdapter.setOnItemClickListener(categoryFragment, cid);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_home);

        mRecyclerView.setAdapter(mAdapter);*/
//        mAdapter.notifyDataSetChanged();
        homeFragment.showSubCategoryConfirm(list, cid);
    }

    public void categoryListened(String cid) {
        homePresenter.categoryClicked(cid);
        Log.i("aaa4", cid);
    }

    public void categoryListened(String cid, String scid) {
        homePresenter.categoryClicked(cid, scid);
        Log.i("subcategorylistened", cid);
        Log.i("subcategorylistened", scid);
    }

    @Override
    public void categoryClickedConfirmed(String cid) {
        imageLoader = new ImageLoader(this, homeFragment);
        imageLoader.loadSubCategoryImage(userId, apiKey, cid);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){
            getSupportFragmentManager().popBackStack();
        }
        else {
            homePresenter.comeBack();
        }

//        homePresenter.comeBack();
/*        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public void comBackConfirm() {
        homeFragment.resetCategory();
/*        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }*/
    }


    @Override
    public void categoryClickedConfirmed(String cid, String scid) {
        imageLoader = new ImageLoader(this, homeFragment);
        imageLoader.loadProductList(userId, apiKey, cid, scid);
    }

    @Override
    public void showProductList(List<Product> resList) {
/*        ProductListAdapter mAdapter = new ProductListAdapter(this, resList);
        mAdapter.setClickListener(categoryFragment);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_home);
        mRecyclerView.setAdapter(mAdapter);*/
        homeFragment.showProductListConfirm(resList);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void dataTransferMethod(Product product) {

/*        Bundle bundle = new Bundle();
        bundle.putString("id", product.getId());
        bundle.putString("pname", product.getPname());
        bundle.putString("quantity", product.getQuantity());
        bundle.putString("prize", product.getPrize());
        bundle.putString("description", product.getDescription());
        bundle.putString("image", product.getImage());

        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);*/

//        getSupportFragmentManager().beginTransaction().replace(R.id.content_home, productFragment, null).addToBackStack(null).commit();

/*        Intent i = new Intent(this, ProductActivity.class);
        i.putExtra("id", product.getId());
        i.putExtra("pname", product.getPname());
        i.putExtra("quantity", product.getQuantity());
        i.putExtra("prize", product.getPrize());
        i.putExtra("description", product.getDescription());
        i.putExtra("image", product.getImage());
        startActivity(i);*/


/*        Bundle bundle = new Bundle();
        bundle.putString("pname", product.getPname());
        bundle.putString("quantity", product.getQuantity());
        bundle.putString("prize", product.getPrize());
        bundle.putString("discription", product.getDescription());
        bundle.putString("image", product.getImage());
        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_home, productFragment).addToBackStack("home_page").commit();*/

    }

}
