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
import com.example.cln62.onlineshoppingapp.pojo.LoginProfile;
import com.example.cln62.onlineshoppingapp.ui.home.HomeActivity;
import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.ui.signup.SignupActivity;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;
import com.example.cln62.onlineshoppingapp.utils.RememberPrefrence;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginPresenter loginPresenter;
    TextView textViewSignup;
    EditText etMobile, etPassword;
    CheckBox mCbDisplayPassword, rememberMe;
    Button buttonLogin;
    NetworkLogIn networkLogIn;
    MySharedPrefences mySharedPrefences;
    RememberPrefrence rememberPreference;

    private static final String TAG = "MainActivity";
    LoginButton loginButton;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        loginPresenter = new LoginPresenter(this);
        mySharedPrefences = new MySharedPrefences();
        rememberPreference = new RememberPrefrence();

        textViewSignup = findViewById(R.id.textViewSignup);
        etMobile = findViewById(R.id.editTextMb);
        etPassword = findViewById(R.id.etPassword);
        mCbDisplayPassword = findViewById(R.id.cbDisplayPassword);
        rememberMe = findViewById(R.id.checkBoxRemember);

        if (rememberPreference.getRememberOrNot(this)) {
            etMobile.setText(mySharedPrefences.getMobile(this));
            etPassword.setText(mySharedPrefences.getPassword(this));
            rememberMe.setChecked(true);
        }

        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rememberPreference.setRememberOrNot(LoginActivity.this, true);
                }
                else {
                    rememberPreference.setRememberOrNot(LoginActivity.this, false);
                }
            }
        });


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

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button_face);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        networkLogIn = new NetworkLogIn(LoginActivity.this);
                        networkLogIn.login("5732027777", "777777", LoginActivity.this);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
                loginProfile.getEmail(), loginProfile.getMobile(), loginProfile.getAppapikey(), loginProfile.getPassword());

        i.putExtra("id", id);
        i.putExtra("apikey", apikey);
        i.putExtra("name", loginProfile.getFname() + " " + loginProfile.getLname());
        i.putExtra("email", loginProfile.getEmail());
        startActivity(i);
    }

    @Override
    public void loginFailureMessage(String message) {
        Toast.makeText(this, "Username or password is wrong!", Toast.LENGTH_SHORT).show();
    }
}
