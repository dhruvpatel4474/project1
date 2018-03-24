package com.geekcoders.payingguest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.geekcoders.payingguest.Adapter.PGListAdapter;
import com.geekcoders.payingguest.Adapter.PGListRAdapter;
import com.geekcoders.payingguest.Adapter.PaymentAdapter;
import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.Objects.Payment;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();
        initilize();
        PaymentList();
    }

    public void PaymentList() {
        final ArrayList<PGObject> arrayList = new ArrayList<>();

        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Payment");
        bandQuery.include("PGDetail");


            bandQuery.whereEqualTo("senderId",  Constant.getValueForKeyString("userId"));

        bandQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects != null && objects.size() > 0) {

                    for (int i = 0; i < objects.size(); i++) {

//                        final Payment obj = new Payment();
//                        int price = objects.get(i).getInt("price");
//                        String recieverId = objects.get(i).getString("recieverId");
//                        String recieverName = objects.get(i).getString("recieverName");
//                        String senderId = objects.get(i).getString("senderId");
//                        String senderName = objects.get(i).getString("senderName");
//                        Date date = objects.get(i).getCreatedAt();
//                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                        String fdate = df.format(date);
//                        obj.setDate(fdate);
//
//                        obj.setPrice(price);
//                        obj.setObjectId(objects.get(i).getObjectId());
//                        obj.setRecieverId(recieverId);
//                        obj.setRecieverName(recieverName);
//                        obj.setSenderId(senderId);
//                        obj.setSenderName(senderName);

                        final PGObject obj = new PGObject();
                        String title = objects.get(i).getString("title");
                        String objectId = objects.get(i).getObjectId();
                        int price = objects.get(i).getInt("price");
                        String city = objects.get(i).getString("city");
                        String description = objects.get(i).getString("description");
                        String userId = objects.get(i).getString("userId");
                        String address = objects.get(i).getString("address");
                        String number = objects.get(i).getString("number");
                        String userName = objects.get(i).getString("userName");
                        String categoryId = objects.get(i).getString("categoryId");
                        String image = objects.get(i).getString("image");
                        Date date = objects.get(i).getCreatedAt();
                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        String fdate = df.format(date);
                        obj.setDate(fdate);

                        obj.setTitle(title);
                        obj.setObjectId(objectId);
                        obj.setPrice(price);
                        obj.setCity(city);
                        obj.setDescription(description);
                        obj.setUserId(userId);
                        obj.setAddress(address);
                        obj.setNumber(number);
                        obj.setUsername(userName);
                        obj.setCategoryId(categoryId);
                      //  obj.setImage(image);


                        arrayList.add(obj);


                    }

                    PGListRAdapter pgListRAdapter = new PGListRAdapter(HistoryActivity.this,arrayList);
                    recyclerView.setAdapter(pgListRAdapter);
                    //listView.setAdapter(adapter);
//                    dialog.cancel();

                } else {
//                    dialog.cancel();


                }


            }
        });

    }

    private void initilize() {

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

    }

}
