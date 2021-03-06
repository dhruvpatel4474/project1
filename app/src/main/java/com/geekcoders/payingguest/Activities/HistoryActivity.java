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
import com.geekcoders.payingguest.Utils.Dialog;
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
        Dialog.showDialog(HistoryActivity.this);
        final ArrayList<PGObject> arrayList = new ArrayList<>();

        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Payment");
        bandQuery.include("PGDetail");
        bandQuery.orderByDescending("createdAt");


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

                        ParseObject  object= objects.get(i).getParseObject("PGDetail");
                        final PGObject obj = new PGObject();
                        String title = object.getString("title");
                        String objectId = object.getObjectId();
                        int price = object.getInt("price");
                        String city = object.getString("city");
                        String description = object.getString("description");
                        String userId = object.getString("userId");
                        String address = object.getString("address");
                        String number = object.getString("number");
                        String userName = object.getString("userName");
                        String categoryId = object.getString("categoryId");
                        String image = object.getString("image");
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
                       obj.setImage(image);


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

        Dialog.closeDialog();

    }

    private void initilize() {

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

    }

}
