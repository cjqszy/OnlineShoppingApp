package com.example.cln62.onlineshoppingapp.ui.signup;

public interface SignupContract {

    interface Presenter{
        void finishActivity();

        void signup();
    }

    interface View{
        void finishActivityConfirm();

        void signupConfirm();
    }

}
