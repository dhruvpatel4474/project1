package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Constant.mcontext = SplashActivity.this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (!Constant.getValueForKeyBoolean("isLogin")) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }




            }
        }, 3000);


    }


    }

