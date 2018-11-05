package com.example.cln62.onlineshoppingapp.data;

import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.ui.checkout.CartActivity;
import com.example.cln62.onlineshoppingapp.ui.home.HomeActivity;

import java.util.List;

public class CartRepository implements CartInterface {

    CartInterface cartDao;

    public CartRepository(HomeActivity homeActivity) {
        cartDao = new CartDao(homeActivity);
    }

    public CartRepository(CartActivity cartActivity) {
        cartDao = new CartDao(cartActivity);
    }


    @Override
    public void addProduct(Product product) {
        cartDao.addProduct(product);
    }

    @Override
    public List<Product> getCartList() {
        return null;
    }

    @Override
    public void addQuantity(int pid, int quantity) {
        cartDao.addQuantity(pid, quantity);
    }

    @Override
    public void deleteQuantity(int pid, int quantity) {
        cartDao.deleteQuantity(pid, quantity);
    }

    @Override
    public void removeItem(int pid) {
        cartDao.removeItem(pid);
    }

}
