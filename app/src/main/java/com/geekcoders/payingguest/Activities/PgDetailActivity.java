package com.geekcoders.payingguest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class PgDetailActivity extends AppCompatActivity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_detail);
        Parse.initialize(PgDetailActivity.this);
        Initiliztion();

        PGObject object=Constant.pgObject;
        title.setText(object.getName().toString());


    }
    private void Initiliztion(){

        title=(TextView)findViewById(R.id.title);

    }

//    private void GetPGData(){
//
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
//        query.getInBackground(Constant.PGId, new GetCallback<ParseObject>() {
//            public void done(ParseObject object, ParseException e) {
//                if (e == null) {
//                    String title = object.getString("title");
//                } else {
//                    // something went wrong
//                }
//            }
//        });
//
//    }

}
