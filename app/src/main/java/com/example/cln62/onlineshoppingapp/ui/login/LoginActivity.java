package com.example.cln62.onlineshoppingapp.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cln62.onlineshoppingapp.network.NetworkLogIn;
import com.example.cln62.onlineshoppingapp.pojo.LoginProfile;
import com.example.cln62.onlineshoppingapp.ui.home.HomeActivity;
import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.ui.signup.SignupActivity;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginPresenter loginPresenter;
    TextView textViewSignup;
    EditText etMobile, etPassword;
    CheckBox mCbDisplayPassword;
    Button buttonLogin;
    NetworkLogIn networkLogIn;
    MySharedPrefences mySharedPrefences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);
        mySharedPrefences = new MySharedPrefences();

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
    protected void onStart() {
        super.onStart();

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
        networkLogIn = new NetworkLogIn(this);
        networkLogIn.login(mobile, password, this);
    }

    @Override
    public void loginSuccess(LoginProfile loginProfile) {
        Toast.makeText(this, "Welcome " + loginProfile.getFname(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        String id = loginProfile.getId();
        String apikey = loginProfile.getAppapikey();
        mySharedPrefences.setUserInfo(this, id, loginProfile.getFname(), loginProfile.getLname(),
                loginProfile.getEmail(), loginProfile.getMobile(), loginProfile.getAppapikey());

        i.putExtra("id", id);
        i.putExtra("apikey", apikey);
        startActivity(i);
    }
}
