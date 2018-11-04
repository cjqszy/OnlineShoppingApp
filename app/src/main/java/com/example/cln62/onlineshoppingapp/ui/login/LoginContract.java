package com.example.cln62.onlineshoppingapp.ui.login;

import com.example.cln62.onlineshoppingapp.pojo.LoginProfile;

public interface LoginContract {
    
    interface Presenter{
        void initListener();

        void signUp();

        void login();
    }
    
    interface View{
        void initListenerConfirm();

        void signUpConfirm();

        void loginConfirm();

        void loginSuccess(LoginProfile loginProfile);

        void loginFailureMessage();
    }
}
