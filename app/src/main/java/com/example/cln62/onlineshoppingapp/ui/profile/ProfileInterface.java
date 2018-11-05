package com.example.cln62.onlineshoppingapp.ui.profile;

public interface ProfileInterface {

    interface Presenter{
        void initProfile();

        void resetPassword();

        void resetPassword(PasswordResetActivity passwordResetActivity);
    }

    interface ProfileView{
        void initProfileConfirm();

        void resetPasswordConfirm();
    }

    interface ResetPasswordView{

        void resetPasswordConfirm();

        void resetResponse(String message);
    }
}
