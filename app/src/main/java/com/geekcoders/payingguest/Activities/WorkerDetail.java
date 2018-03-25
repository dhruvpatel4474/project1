package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekcoders.payingguest.Objects.Worker;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.geekcoders.payingguest.Utils.Fonts;
import com.squareup.picasso.Picasso;


public class WorkerDetail extends AppCompatActivity {
    private TextView title, disc;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView img;
    private TextView workType;
    private TextView city;
    private Button callBtn;
    private TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_detail);
        title = (TextView) findViewById(R.id.worktitle);
        workType = (TextView) findViewById(R.id.worktype);
        city = (TextView) findViewById(R.id.workcity);
        disc = (TextView) findViewById(R.id.workdetail);
        price = (TextView) findViewById(R.id.workprice);
        img = (ImageView) findViewById(R.id.bgheader_w);

        callBtn = (Button) findViewById(R.id.workcall);

        getSupportActionBar().hide();
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar_w);
//        setSupportActionBar(toolbar);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar_w);
        collapsingToolbarLayout.setTitle(" ");

        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(WorkerDetail.this, R.color.colorPrimary));

        final Worker workerObj = Constant.workerObj;


        title.setText(workerObj.getTitle());
        workType.setText(workerObj.getWorkType());
        city.setText(workerObj.getCity());
        price.setText("₹ " + workerObj.getPrice());
        disc.setText(workerObj.getDescription());

        //btime.setText(object.getAuc_EndingDate().toString());
        Picasso.with(WorkerDetail.this)
                .load(workerObj.getImage())
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .into(img);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + workerObj.getNumber()));
                startActivity(intent);
            }
        });


    }

}
