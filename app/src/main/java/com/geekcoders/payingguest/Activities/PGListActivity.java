package com.geekcoders.payingguest.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.geekcoders.payingguest.Adapter.CategoryAdapter;
import com.geekcoders.payingguest.Adapter.PGListAdapter;
import com.geekcoders.payingguest.Adapter.PGListRAdapter;
import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PGListActivity extends AppCompatActivity {

    private ArrayList<PGObject> arrayList;
    private PGListAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pglist);
        Parse.initialize(PGListActivity.this);
        getSupportActionBar().hide();
        Constant.mcontext=PGListActivity.this;
        Initiliztion();
        PgList();
    }

    private void Initiliztion() {

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

    }


    public void PgList() {
        arrayList = new ArrayList<>();

        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("PGDetail");

        final ProgressDialog dialog = new ProgressDialog(PGListActivity.this);
        dialog.setMessage("Please wait");
        dialog.show();

//
//        if (band_type == 0) {
//            bandQuery.whereEqualTo("creator", ParseUser.getCurrentUser());
//        } else {
//            bandQuery.whereContainedIn("bandMembers", Collections.singletonList(ParseUser.getCurrentUser()));
//        }
        bandQuery.whereEqualTo("categoryId", Constant.getValueForKeyString("categoryId"));
        bandQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects != null && objects.size() > 0) {

                    for (int i = 0; i < objects.size(); i++) {

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


                        // FetchListOfVenuesForSelectedBand(objects.get(i));
                        //ImagesBand(objects.get(i));


//                        ParseQuery<ParseObject> imgQuery = ParseQuery.getQuery("RCBandImage");
//                        imgQuery.whereEqualTo("band", objects.get(i));
//                        imgQuery.findInBackground(new FindCallback<ParseObject>() {
//                            @Override
//                            public void done(List<ParseObject> objects, ParseException e) {
//                                if (e == null && objects.size() != 0) {
//                                    final ParseObject object = objects.get(0);
//                                    final ParseFile fileObject = (ParseFile) object.get("imageFile");
//                                    fileObject.getDataInBackground(new GetDataCallback() {
//                                        @Override
//                                        public void done(byte[] data, ParseException e) {
//                                            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
//                                            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
//                                            objrcband.setImage(bitmap);
//                                            adapter.notifyDataSetChanged();
//                                        }
//                                    });
//                                }
//                            }
//                        });
                        arrayList.add(obj);

                    }

//                    adapter = new PGListAdapter(PGListActivity.this, arrayList);
                    PGListRAdapter pgListRAdapter = new PGListRAdapter(PGListActivity.this,arrayList);
                    try {
                        recyclerView.setAdapter(pgListRAdapter);
                    }catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }

                    dialog.cancel();

                } else {
                    dialog.cancel();


                }


            }
        });

    }

}
