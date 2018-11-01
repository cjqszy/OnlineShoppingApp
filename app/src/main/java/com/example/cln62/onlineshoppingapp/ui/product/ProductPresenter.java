package com.example.cln62.onlineshoppingapp.ui.product;

import com.example.cln62.onlineshoppingapp.data.CartInterface;
import com.example.cln62.onlineshoppingapp.data.CartRepository;
import com.example.cln62.onlineshoppingapp.pojo.Product;

public class ProductPresenter implements ProductContract.Presenter {

    ProductContract.View view;
    CartInterface cartRepository;

    public ProductPresenter(ProductActivity productActivity) {
        view = productActivity;
        cartRepository = new CartRepository(productActivity);
    }


    @Override
    public void addToCart(Product product) {
        cartRepository.addProduct(product);
    }
}
