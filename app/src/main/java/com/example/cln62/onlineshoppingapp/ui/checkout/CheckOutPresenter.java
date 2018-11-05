package com.example.cln62.onlineshoppingapp.ui.checkout;

public class CheckOutPresenter implements CheckOutInterface.Presenter {
    CheckOutInterface.View view;

    public CheckOutPresenter(CartActivity cartActivity) {
        view = cartActivity;
    }

    @Override
    public void applyCoupon(double couponNum ) {
        view.applyCouponConfirm(couponNum);
    }
}
