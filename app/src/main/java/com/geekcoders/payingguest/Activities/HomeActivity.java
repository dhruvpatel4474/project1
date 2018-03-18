package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.geekcoders.payingguest.Adapter.CategoryAdapter;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout lineLayCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        intialize();

        lineLayCategory.setOnClickListener(this);

        Constant.mcontext = HomeActivity.this;
        if (!Constant.getValueForKeyBoolean("isLogin")) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void intialize()
    {
        lineLayCategory = (LinearLayout)findViewById(R.id.lineLay_category);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.lineLay_category :
                startActivity(new Intent(HomeActivity.this,CategoryActivity.class));
                break;
        }
    }
}
