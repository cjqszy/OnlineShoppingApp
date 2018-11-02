package com.example.cln62.onlineshoppingapp.ui.home;

import android.util.Log;

public class HomePresenter implements HomeContract.Presenter{

    HomeContract.View view;

    public HomePresenter(HomeActivity homeActivity) {
        view = homeActivity;
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
}
