package com.example.cln62.onlineshoppingapp.ui.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.network.NetworkSignUp;
import com.example.cln62.onlineshoppingapp.pojo.SignupProfile;

public class SignupActivity extends AppCompatActivity implements SignupContract.View {

    SignupPresenter signupPresenter;
    TextView textViewLogin;
    Button signupButton;
    NetworkSignUp networkSignIn;
    EditText editTextFn, editTextLn, editTextEm, editTextAd, editTextMobile, editTextPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupPresenter = new SignupPresenter(this);
        signupButton = findViewById(R.id.buttonSignup);
        editTextFn = findViewById(R.id.editTextFn);
        editTextLn = findViewById(R.id.editTextLn);
        editTextEm = findViewById(R.id.editTextEm);
        editTextAd = findViewById(R.id.editTextAd);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextPwd = findViewById(R.id.editTextPwd);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextMobile.getText().toString().length() < 10) {
                    Toast.makeText(SignupActivity.this, "The length of mobile number should be greater than 10",
                            Toast.LENGTH_SHORT).show();
                }
                if (editTextPwd.getText().toString().length() < 10) {
                    Toast.makeText(SignupActivity.this, "The length of password should be greater than 10",
                            Toast.LENGTH_SHORT).show();
                }
                signupPresenter.signup();
            }
        });

        textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupPresenter.finishActivity();
            }
        });
    }

    @Override
    public void finishActivityConfirm() {
        finish();
    }

    @Override
    public void signupConfirm() {
        String fn = editTextFn.getText().toString();
        String ln = editTextLn.getText().toString();
        String em = editTextEm.getText().toString();
        String ad = editTextAd.getText().toString();
        String mb = editTextMobile.getText().toString();
        String pwd = editTextPwd.getText().toString();

        networkSignIn = new NetworkSignUp();
        networkSignIn.signup(new SignupProfile(fn, ln, em, ad, mb, pwd));
    }
}
