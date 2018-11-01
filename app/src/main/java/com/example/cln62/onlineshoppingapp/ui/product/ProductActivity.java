package com.example.cln62.onlineshoppingapp.ui.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.squareup.picasso.Picasso;

import static com.example.cln62.onlineshoppingapp.pojo.Product.PRODUCT_IMAGE_KEY;
import static com.example.cln62.onlineshoppingapp.pojo.Product.PRODUCT_NAME_KEY;

public class ProductActivity extends AppCompatActivity implements ProductContract.View {

    TextView textViewPName;
    Button button_addtocart;
    ImageView imageProduct;
    ProductPresenter productPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productPresenter = new ProductPresenter(this);
        textViewPName = findViewById(R.id.textView_productname2);

//        Bundle bundle = getArguments();
        String productId = getIntent().getStringExtra("id");
        String productName = getIntent().getStringExtra(PRODUCT_NAME_KEY);
        String productImage = getIntent().getStringExtra(PRODUCT_IMAGE_KEY);
        String productPrice = getIntent().getStringExtra("prize");
        String productDescription = getIntent().getStringExtra("description");
        String productQuantity = getIntent().getStringExtra("quantity");
        textViewPName.setText(productName);

        button_addtocart = findViewById(R.id.button_addtocart2);
        imageProduct = findViewById(R.id.image_product2);
        Picasso.with(this)
                .load(productImage)
                .into(imageProduct);

        final Product product = new Product(productId, productName, productQuantity, productPrice,
                productDescription, productImage);

        button_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                productPresenter.addToCart(product);
            }
        });




    }
}
