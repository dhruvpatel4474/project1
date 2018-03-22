package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.geekcoders.payingguest.Adapter.CategoryAdapter;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.PushService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout lineLayCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        Parse.initialize(HomeActivity.this);

        intialize();

        lineLayCategory.setOnClickListener(this);

        Constant.mcontext = HomeActivity.this;
        if (!Constant.getValueForKeyBoolean("isLogin")) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
//            ParseUser user = ParseUser.getCurrentUser();
//            Log.d("log", "d");
//        if (user != null) {
//
//        }
            ParseInstallation.getCurrentInstallation().saveInBackground();

            // ParseQuery pushQuery = ParseInstallation.getQuery();


// Send push notification to query
//        ParsePush push = new ParsePush();
//       push.setChannel("NuDYCP0HdC");
//        push.setMessage("Willie Hayes injured by own pop fly.");
//        push.sendInBackground();


//                    ParseQuery searchquery = ParseQuery.getQuery("User");
//        searchquery.whereEqualTo("objectId", "NuDYCP0HdC");



        // Create our Installation query
//        ParseQuery pushQuery = ParseInstallation.getQuery();
//        pushQuery.whereEqualTo("userId","NuDYCP0HdC" );

// Send push notification to query
//        ParsePush push = new ParsePush();
//        push.setChannel("NuDYCP0HdC");
//        push.setMessage("test");
//        push.sendInBackground();




        }
    }

    public void intialize() {
        lineLayCategory = (LinearLayout) findViewById(R.id.lineLay_category);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lineLay_category:
                startActivity(new Intent(HomeActivity.this, CategoryActivity.class));
                break;
        }
    }
}
