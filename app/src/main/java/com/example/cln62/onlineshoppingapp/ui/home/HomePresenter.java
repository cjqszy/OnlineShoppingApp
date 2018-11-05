package com.example.cln62.onlineshoppingapp.ui.home;

import android.util.Log;

import com.example.cln62.onlineshoppingapp.data.CartInterface;
import com.example.cln62.onlineshoppingapp.data.CartRepository;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.ui.checkout.CartActivity;
import com.example.cln62.onlineshoppingapp.ui.checkout.CheckOutInterface;

public class HomePresenter implements HomeContract.Presenter{

    HomeContract.View view;
    CheckOutInterface.View cartView;
    CartInterface cartRepository;

    public HomePresenter(HomeActivity homeActivity) {
        view = homeActivity;
        cartRepository = new CartRepository(homeActivity);
    }

    public HomePresenter(CartActivity cartActivity) {
        cartView = cartActivity;
        cartRepository = new CartRepository(cartActivity);
    }

    @Override
    public void cartCllicked() {
        view.proceedCart();
    }

    @Override
    public void initView() {
        view.initViewConfirm();
    }

    @Override
    public void categoryClicked(String cid) {
        view.categoryClickedConfirmed(cid);
        Log.i("aaa5", cid);
    }

    @Override
    public void comeBack() {
        view.comBackConfirm();
    }

    @Override
    public void categoryClicked(String cid, String scid) {
        view.categoryClickedConfirmed(cid, scid);
    }

    @Override
    public void addToCart(Product product) {

        cartRepository.addProduct(product);
    }

    @Override
    public void add(int pid, int quantity) {
        if (cartRepository == null) {
            Log.i("WTF","wtf");
            return;
        }
        cartRepository.addQuantity(pid, quantity);
    }

    @Override
    public void delete(int pid, int quantity) {
        cartRepository.deleteQuantity(pid, quantity);
    }

    @Override
    public void remove(int pid) {
        cartRepository.removeItem(pid);
    }
}
