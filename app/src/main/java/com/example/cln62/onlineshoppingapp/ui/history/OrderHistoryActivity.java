package com.example.cln62.onlineshoppingapp.ui.history;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cln62.onlineshoppingapp.R;

public class OrderHistoryActivity extends AppCompatActivity implements OrderHistoryInterface.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
    }
}
