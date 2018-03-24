package com.geekcoders.payingguest.Activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.geekcoders.payingguest.Adapter.PGListAdapter;
import com.geekcoders.payingguest.Adapter.PaymentAdapter;
import com.geekcoders.payingguest.Adapter.PaymentRAdapter;
import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.Objects.Payment;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewPaymentActivity extends AppCompatActivity {

    public boolean IsRecieved=true;
    private PaymentAdapter adapter;
    private LinearLayout lineLayPaid,lineLayRecieved;
    private TextView tvPaid,tvRecieved;
    private RecyclerView recyclerView;
    ListView listView;
    private CardView cardPaid,cardRecieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment);
        getSupportActionBar().hide();
        Parse.initialize(ViewPaymentActivity.this);
        Constant.mcontext=ViewPaymentActivity.this;
        initialize();
        PaymentList(); // default received

        lineLayPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IsRecieved=false;
                PaymentList();
            }
        });
        lineLayRecieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IsRecieved=true;
                PaymentList();
            }
        });


    }



    public void PaymentList() {
       final ArrayList<Payment> arrayList = new ArrayList<>();

        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Payment");

        if (IsRecieved) {
            bandQuery.whereEqualTo("recieverId", Constant.getValueForKeyString("userId"));
        } else {
            bandQuery.whereEqualTo("senderId",  Constant.getValueForKeyString("userId"));
        }
        bandQuery.orderByDescending("createdAt");
        bandQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects != null && objects.size() > 0) {

                    for (int i = 0; i < objects.size(); i++) {

                        final Payment obj = new Payment();
                        int price = objects.get(i).getInt("price");
                        String recieverId = objects.get(i).getString("recieverId");
                        String recieverName = objects.get(i).getString("recieverName");
                        String senderId = objects.get(i).getString("senderId");
                        String senderName = objects.get(i).getString("senderName");
                        Date date = objects.get(i).getCreatedAt();
                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        String fdate = df.format(date);
                        obj.setDate(fdate);

                        obj.setPrice(price);
                        obj.setStatus(IsRecieved);
                        obj.setObjectId(objects.get(i).getObjectId());
                        obj.setRecieverId(recieverId);
                        obj.setRecieverName(recieverName);
                        obj.setSenderId(senderId);
                        obj.setSenderName(senderName);

                        arrayList.add(obj);


                    }

//                    adapter = new PaymentAdapter(ViewPaymentActivity.this, arrayList);
//                    listView.setAdapter(adapter);
//                    dialog.cancel();
                    PaymentRAdapter paymentRAdapter = new PaymentRAdapter(ViewPaymentActivity.this,arrayList);
                    Log.e("array size",arrayList.size()+ "");
                    recyclerView.setAdapter(paymentRAdapter);
                    try {

                        if (IsRecieved)
                        {
                            cardRecieved.setCardElevation(3);
                            cardPaid.setCardElevation(0);
                            cardPaid.setBackgroundResource(R.color.grey);
                            cardRecieved.setBackgroundResource(R.color.white);

                        }
                        else {
                            cardPaid.setCardElevation(3);
                            cardPaid.setBackgroundResource(R.color.white);
                            cardRecieved.setBackgroundResource(R.color.grey);
                            cardRecieved.setCardElevation(0);
                        }
                    }catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }
                } else {
//                    dialog.cancel();


                }


            }
        });

    }

    public void initialize()
    {
        lineLayPaid = (LinearLayout)findViewById(R.id.lineLay_paid);
        lineLayRecieved = (LinearLayout)findViewById(R.id.lineLay_recieved);
        tvPaid = (TextView)findViewById(R.id.tv_paid);
        tvRecieved = (TextView)findViewById(R.id.tv_recieved);
        cardPaid = (CardView)findViewById(R.id.card_paid);
        cardRecieved = (CardView)findViewById(R.id.card_recieved);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
    }
}
