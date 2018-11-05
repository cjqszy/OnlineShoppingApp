package com.example.cln62.onlineshoppingapp.ui.profile;

public class ProfilePresenter implements ProfileInterface.Presenter {

    ProfileInterface.ProfileView view;

    public ProfilePresenter(ProfileActivity profileActivity) {
        view = profileActivity;
    }

    public ProfilePresenter() {

    }

    @Override
    public void initProfile() {
        view.initProfileConfirm();
    }

    @Override
    public void resetPassword() {
        view.resetPasswordConfirm();
    }

    @Override
    public void resetPassword(PasswordResetActivity passwordResetActivity) {
        passwordResetActivity.resetPasswordConfirm();
    }
}
