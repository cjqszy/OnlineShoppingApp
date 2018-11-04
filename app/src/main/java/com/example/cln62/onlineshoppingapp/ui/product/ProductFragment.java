package com.example.cln62.onlineshoppingapp.ui.product;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.ui.home.HomeActivity;
import com.example.cln62.onlineshoppingapp.ui.home.HomePresenter;
import com.squareup.picasso.Picasso;

import static com.example.cln62.onlineshoppingapp.pojo.Product.PRODUCT_IMAGE_KEY;
import static com.example.cln62.onlineshoppingapp.pojo.Product.PRODUCT_NAME_KEY;

public class ProductFragment extends Fragment {
//    public View myRoot;

    TextView textViewPName;
    Button button_addtocart;
    ImageView imageProduct;
    HomePresenter homePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);
        homePresenter = new HomePresenter(((HomeActivity)getActivity()));
        textViewPName = view.findViewById(R.id.textView_productname2);

        Bundle bundle = getArguments();
        String productId = bundle.getString("id");
        String productName = bundle.getString(PRODUCT_NAME_KEY);
        String productImage = bundle.getString(PRODUCT_IMAGE_KEY);
        String productPrice = bundle.getString("prize");
        String productDescription = bundle.getString("description");
        String productQuantity = bundle.getString("quantity");
        textViewPName.setText(productName);

        button_addtocart = view.findViewById(R.id.button_addtocart2);
        imageProduct = view.findViewById(R.id.image_product2);
        Picasso.with(getContext())
                .load(productImage)
                .into(imageProduct);

        final Product product = new Product(productId, productName, productQuantity, productPrice,
                productDescription, productImage);

        button_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Added to cart", Toast.LENGTH_SHORT).show();
                if (homePresenter == null) {
                    Toast.makeText(getActivity(), "Added to cart2", Toast.LENGTH_SHORT).show();
                }
                else {
                    homePresenter.addToCart(product);
                }
            }
        });

        return view;
    }
}
