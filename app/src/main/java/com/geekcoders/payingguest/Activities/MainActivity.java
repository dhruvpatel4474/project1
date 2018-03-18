package com.geekcoders.payingguest.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.geekcoders.payingguest.R;
import com.parse.Parse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(this);
        getSupportActionBar().hide();
    }
}
