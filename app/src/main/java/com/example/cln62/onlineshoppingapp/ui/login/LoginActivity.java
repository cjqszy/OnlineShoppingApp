package com.example.cln62.onlineshoppingapp.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cln62.onlineshoppingapp.network.NetworkLogIn;
import com.example.cln62.onlineshoppingapp.ui.home.HomeActivity;
import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.ui.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginPresenter loginPresenter;
    TextView textViewSignup;
    EditText etMobile, etPassword;
    CheckBox mCbDisplayPassword;
    Button buttonLogin;
    NetworkLogIn networkLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);

        textViewSignup = findViewById(R.id.textViewSignup);
        etMobile = findViewById(R.id.editTextMb);
        etPassword = findViewById(R.id.etPassword);
        mCbDisplayPassword = findViewById(R.id.cbDisplayPassword);

        loginPresenter.initListener();

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.signUp();
            }
        });

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
            }
        });
    }

    @Override
    public void initListenerConfirm() {
        mCbDisplayPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void signUpConfirm() {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }

    @Override
    public void loginConfirm() {
        String mobile = etMobile.getText().toString();
        String password = etPassword.getText().toString();
        networkLogIn = new NetworkLogIn();
        networkLogIn.login(mobile, password, this);

    }

    @Override
    public void loginSuccess(String username) {
        Toast.makeText(this, "Welcome " + username, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
    }
}
