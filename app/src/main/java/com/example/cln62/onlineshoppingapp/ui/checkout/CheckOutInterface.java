package com.example.cln62.onlineshoppingapp.ui.checkout;

public interface CheckOutInterface {

    interface View{
        void applyCouponConfirm(double couponNum);

        void applyDiscount(String discount);
    }

    interface Presenter{
        void applyCoupon(double couponNum);
    }
}
