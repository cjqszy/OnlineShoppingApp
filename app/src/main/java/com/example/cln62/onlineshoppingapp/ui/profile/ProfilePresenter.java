package com.example.cln62.onlineshoppingapp.ui.profile;

public class ProfilePresenter implements ProfileInterface.Presenter {

    ProfileInterface.View view;

    public ProfilePresenter(ProfileActivity profileActivity) {
        view = profileActivity;
    }

    @Override
    public void initProfile() {
        view.initProfileConfirm();
    }
}
