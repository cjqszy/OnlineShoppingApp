package com.example.cln62.onlineshoppingapp.ui.home;

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
}
