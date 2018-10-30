package com.example.cln62.onlineshoppingapp.ui.login;

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

        void loginSuccess(String username);
    }
}
