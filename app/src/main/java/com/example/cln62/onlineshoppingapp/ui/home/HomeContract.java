package com.example.cln62.onlineshoppingapp.ui.home;

import com.example.cln62.onlineshoppingapp.pojo.CatSubcategory;
import com.example.cln62.onlineshoppingapp.pojo.Product;

import java.util.List;

public interface HomeContract {

    interface Presenter{
        void cartCllicked();

        void initView();

        void categoryClicked(String cid);

        void comeBack();

        void categoryClicked(String cid, String scid);
    }

    interface View{

        void proceedCart();

        void initViewConfirm();

        void categoryClickedConfirmed(String subId);

        void showSubCategory(List<CatSubcategory> list, String cid);

        void comBackConfirm();

        void categoryClickedConfirmed(String cid, String scid);

        void showProductList(List<Product> resList);

        public void dataTransferMethod(Product product);
    };
}
