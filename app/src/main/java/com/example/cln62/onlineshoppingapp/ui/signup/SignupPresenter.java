package com.example.cln62.onlineshoppingapp.ui.signup;

public class SignupPresenter implements SignupContract.Presenter{

    SignupContract.View view;

    public SignupPresenter(SignupActivity signupActivity) {
        view = signupActivity;
    }

    @Override
    public void finishActivity() {
        view.finishActivityConfirm();
    }

    @Override
    public void signup() {
        view.signupConfirm();
    }
}
