package com.example.cln62.onlineshoppingapp.data;

import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.ui.home.HomeActivity;

import java.util.List;

public class CartRepository implements CartInterface {

    CartInterface cartDao;

    public CartRepository(HomeActivity homeActivity) {
        cartDao = new CartDao(homeActivity);
    }


    @Override
    public void addProduct(Product product) {
        cartDao.addProduct(product);
    }

    @Override
    public List<Product> getCartList() {
        return null;
    }

}
