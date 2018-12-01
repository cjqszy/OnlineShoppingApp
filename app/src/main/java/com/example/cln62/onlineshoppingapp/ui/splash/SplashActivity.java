package com.example.cln62.onlineshoppingapp.ui.splash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageViewSplash);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);

        imageView.startAnimation(animation);

/*        progress = new ProgressDialog(this);
        progress.setTitle("MAIN CAT LOADING");
        progress.setCancelable(false);
        progress.show();*/

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                progress.dismiss();
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        };

        thread.start();

/*        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);*/
    }
}
