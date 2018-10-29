package com.example.cln62.onlineshoppingapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.cln62.onlineshoppingapp.HomeActivity;
import com.example.cln62.onlineshoppingapp.LoginActivity;
import com.example.cln62.onlineshoppingapp.MainActivity;
import com.example.cln62.onlineshoppingapp.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.imageViewSplash);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);

        imageView.startAnimation(animation);

/*        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        };

        thread.start();*/

        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
