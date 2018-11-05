package com.example.cln62.onlineshoppingapp.ui.product;

import com.example.cln62.onlineshoppingapp.pojo.Product;

public interface ProductContract  {

    interface View{}

    interface Presenter{
        void addToCart(Product product);
    }
}
