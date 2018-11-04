package com.example.cln62.onlineshoppingapp.ui.home;

import android.util.Log;

import com.example.cln62.onlineshoppingapp.data.CartInterface;
import com.example.cln62.onlineshoppingapp.data.CartRepository;
import com.example.cln62.onlineshoppingapp.pojo.Product;

public class HomePresenter implements HomeContract.Presenter{

    HomeContract.View view;
    CartInterface cartRepository;

    public HomePresenter(HomeActivity homeActivity) {
        view = homeActivity;
        cartRepository = new CartRepository(homeActivity);
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
}
