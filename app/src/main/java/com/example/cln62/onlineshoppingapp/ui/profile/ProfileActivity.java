package com.example.cln62.onlineshoppingapp.ui.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;

public class ProfileActivity extends AppCompatActivity implements ProfileInterface.View{

    TextView textViewFn, textViewLn, textViewEm, textViewMobile;
    Button updateImage, resetPwd;
    MySharedPrefences mySharedPrefences;
    ProfilePresenter profilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mySharedPrefences = new MySharedPrefences();
        profilePresenter = new ProfilePresenter(this);

        textViewFn = findViewById(R.id.textViewFn);
        textViewLn = findViewById(R.id.textViewLn);
        textViewEm = findViewById(R.id.textViewEm);
        textViewMobile = findViewById(R.id.textViewMobile);
        updateImage = findViewById(R.id.updateImage);
        resetPwd = findViewById(R.id.resetPwd);

        profilePresenter.initProfile();
    }

    @Override
    public void initProfileConfirm() {
        textViewFn.setText("FirstName:   " + mySharedPrefences.getFName(this));
        textViewLn.setText("LastName:   " + mySharedPrefences.getLName(this));
        textViewEm.setText("Email:   " + mySharedPrefences.getEmail(this));
        textViewMobile.setText("Mobile:   " + mySharedPrefences.getMobile(this));
    }
}
