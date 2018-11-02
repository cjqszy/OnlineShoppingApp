package com.example.cln62.onlineshoppingapp.data;

import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.ui.product.ProductActivity;

import java.util.List;

public class CartRepository implements CartInterface {

    CartInterface cartDao;

    public CartRepository(ProductActivity productActivity) {
        cartDao = new CartDAO(productActivity);
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
