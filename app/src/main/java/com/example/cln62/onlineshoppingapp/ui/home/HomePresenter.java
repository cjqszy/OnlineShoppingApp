package com.example.cln62.onlineshoppingapp.ui.home;

import android.util.Log;

public class HomePresenter implements HomeContract.Presenter{

    HomeContract.View view;

    public HomePresenter(HomeActivity homeActivity) {
        view = homeActivity;
    }

    @Override
    public void cartCllicked() {
        view.proceedCheckOut();
    }

    @Override
    public void initView() {
        view.initViewConfirm();
    }

    @Override
    public void categoryClicked(String subId) {
        view.categoryClickedConfirmed(subId);
        Log.i("aaa5", subId);
    }

    @Override
    public void comeBack() {
        view.comBackConfirm();
    }
}
