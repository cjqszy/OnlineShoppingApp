package com.example.cln62.onlineshoppingapp.ui.checkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cln62.onlineshoppingapp.R;

public class CheckOutActivity extends AppCompatActivity {

    int testFlag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        if (testFlag == 0) {
            getSupportFragmentManager().beginTransaction().add(R.id.checkoutact_layout, new EmptyCartFragment(), null ).commit();
        }
    }
}
