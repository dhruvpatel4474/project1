package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;

public class HomeActivity extends AppCompatActivity {

    private Button viewCatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Initiliztion();

        if (!Constant.getValueForKeyBoolean("isLogin")){
            Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }


        viewCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,CategoryActivity.class);
                startActivity(intent);
            }
        });

    }
    private void Initiliztion() {


        viewCatBtn = (Button) findViewById(R.id.viewCatBtn);


    }
}
