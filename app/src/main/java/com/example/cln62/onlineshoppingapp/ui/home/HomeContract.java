package com.example.cln62.onlineshoppingapp.ui.home;

public interface HomeContract {

    interface Presenter{
        void cartCllicked();

        void initView();
    }

    interface View{

        void proceedCheckOut();

        void initViewConfirm();
    };
}
