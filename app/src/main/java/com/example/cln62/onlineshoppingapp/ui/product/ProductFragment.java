package com.example.cln62.onlineshoppingapp.ui.product;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cln62.onlineshoppingapp.R;

public class ProductFragment extends Fragment {

    TextView textViewPName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_layout, container, false);

        textViewPName = view.findViewById(R.id.textView_productname);

        Bundle bundle = getArguments();
        String productName = bundle.getString("pname");
        textViewPName.setText(productName);
        return view;
    }
}
