package com.example.cln62.onlineshoppingapp.ui.home;

import com.example.cln62.onlineshoppingapp.pojo.Category;

import java.util.List;

public interface HomeContract {

    interface Presenter{
        void cartCllicked();

        void initView();

        void categoryClicked(String subId);

        void comeBack();
    }

    interface View{

        void proceedCheckOut();

        void initViewConfirm();

        void categoryClickedConfirmed(String subId);

        void showSubCategory(List<Category> resList2);

        void comBackConfirm();
    };
}
