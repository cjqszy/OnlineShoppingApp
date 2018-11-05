package com.example.cln62.onlineshoppingapp.ui.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.network.NetworkResetPassword;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;

public class PasswordResetActivity extends AppCompatActivity implements ProfileInterface.ResetPasswordView {

    NetworkResetPassword networkResetPassword;
    ProfilePresenter profilePresenter;
    EditText editTextCurrentPassword, editTextNewPassword;
    MySharedPrefences mySharedPrefences;
    Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        editTextCurrentPassword = findViewById(R.id.editText_oldpwd);
        editTextNewPassword = findViewById(R.id.editText_newpwd);
        buttonReset = findViewById(R.id.button_reset);
        mySharedPrefences = new MySharedPrefences();
        networkResetPassword = new NetworkResetPassword();
        profilePresenter = new ProfilePresenter();

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextNewPassword.getText().toString().length() < 6) {
                    Toast.makeText(PasswordResetActivity.this,
                            "The length of password should be at least 6", Toast.LENGTH_SHORT).show();
                    return;
                }

                profilePresenter.resetPassword(PasswordResetActivity.this);
            }
        });
    }

    @Override
    public void resetPasswordConfirm() {
        String mobile = mySharedPrefences.getMobile(this);
        String oldPassword = mySharedPrefences.getPassword(this);
/*        if (!oldPassword.equals(editTextCurrentPassword.getText().toString())) {
            Toast.makeText(this, "Current password is wrong!", Toast.LENGTH_SHORT).show();
            return;
        }*/
        networkResetPassword.resetPasswordRequest(this, editTextCurrentPassword.getText().toString(),
                editTextNewPassword.getText().toString(), mobile);
    }

    @Override
    public void resetResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
