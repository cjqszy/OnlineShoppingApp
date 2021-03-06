package com.example.cln62.onlineshoppingapp.ui.checkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.adapter.CartAdapter;
import com.example.cln62.onlineshoppingapp.adapter.ProductListAdapter;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.data.CartDao;
import com.example.cln62.onlineshoppingapp.network.NetworkApplyCoupon;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.ui.home.HomePresenter;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CheckOutInterface.View,
        CartAdapter.ClickListener{

    CheckOutPresenter checkOutPresenter;
    private CartDao cartDAO;
    RecyclerView recyclerView;
    List<Product> cartList;
    ProductListAdapter cartListAdapter;
    Button buttonCheckout, buttonContinueShopping;
    TextView textViewSubTotal, textViewTax, textViewTotal, textViewCoupon;
    boolean couponApplied;
    private ArrayAdapter<String> adapter;
    double sum;
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        checkOutPresenter = new CheckOutPresenter(this);
        buttonCheckout = findViewById(R.id.button_checkout);
        buttonContinueShopping = findViewById(R.id.button_continue_shopping);
        textViewSubTotal = findViewById(R.id.tv_subTotal);
        textViewTax = findViewById(R.id.tv_tax);
        textViewTotal = findViewById(R.id.tv_total);
        textViewCoupon = findViewById(R.id.tv_coupon);
        homePresenter = new HomePresenter(this);

        initDatabase();
        initView();


        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sum < 1) {
                    Toast.makeText(CartActivity.this,
                            "No item in the cart, contnue shopping", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(CartActivity.this, CheckOutActivity.class);
                i.putExtra("payment", sum);
                startActivity(i);
            }
        });

        buttonContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textViewCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sum < 1) {
                    Toast.makeText(CartActivity.this,
                            "No item in the cart, contnue shopping", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!couponApplied) {
                    checkOutPresenter.applyCoupon(Constants.COUPON_NUMBER);
                    couponApplied = true;
                }
                else {
                    Toast.makeText(CartActivity.this, "You have used the coupon!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CartAdapter cartAdapter = new CartAdapter(this, cartList);
        cartAdapter.setOnItemClickListener(this);
//        cartListAdapter = new ProductListAdapter(this, cartList);

        recyclerView.setAdapter(cartAdapter);

        sum = 0;
        for (int i = 0; i < cartList.size(); i++) {
            Product product = cartList.get(i);
            String price = product.getPrize();
            String quantity = product.getQuantity();
            int totalPrice = Integer.parseInt(price) * Integer.parseInt(quantity);
            sum += totalPrice;
        }

        textViewSubTotal.setText("$" + String.valueOf(sum));
        textViewTax.setText("$" + String.valueOf(sum * 0.1));
        sum *= 1.1;
        textViewTotal.setText("$" + String.valueOf(sum));

    }

    private void initDatabase() {
        cartDAO = new CartDao(this);
        cartList = cartDAO.getCartList();

        Log.i("cd", String.valueOf(cartList.size()));
        if (cartList.size() != 0) {
            Log.i("cd",cartList.get(0).getPname());

        }
    }

    @Override
    public void applyCouponConfirm(double couponNum) {
        NetworkApplyCoupon networkApplyCoupon = new NetworkApplyCoupon();
        networkApplyCoupon.getDiscount(this);
    }

    @Override
    public void applyDiscount(String discount) {
        double disc = Double.parseDouble(discount);
        double totalOld = Double.parseDouble((textViewTotal.getText().toString().substring(1)));
        String total = String.valueOf((totalOld * (100 - disc) * 0.01));
        sum = Double.parseDouble(total);
        textViewTotal.setText("$" + sum);
        Log.i("adsad", String.valueOf(disc));
        Toast.makeText(this, "You get a discount of " + disc + "% with this coupon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshPayment() {

        int subtotal = 0;
        for (int i = 0; i < cartList.size(); i++) {
            subtotal = subtotal + (Integer.parseInt(cartList.get(i).getPrize())
                    * Integer.parseInt(cartList.get(i).getQuantity()));
        }

        double taxes = (double) (subtotal * 0.1);
        double total = subtotal + taxes;
        textViewSubTotal.setText("$" + subtotal);
        textViewTax.setText("$" + taxes);
        textViewTotal.setText("$" + total);
    }

    @Override
    public void addClicked(View view, int position, int quantity) {
        int Pid = Integer.parseInt(cartList.get(position).getId());
        homePresenter.add(Pid, quantity);
    }

    @Override
    public void deleteClicked(View view, int position, int quantity) {
        int Pid = Integer.parseInt(cartList.get(position).getId());
        homePresenter.delete(Pid, quantity);
    }

    @Override
    public void removeClicked(View v, int position) {
        int Pid = Integer.parseInt(cartList.get(position).getId());
        homePresenter.remove(Pid);
    }
}
