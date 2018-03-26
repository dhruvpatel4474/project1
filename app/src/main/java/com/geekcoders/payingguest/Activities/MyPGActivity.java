package com.geekcoders.payingguest.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekcoders.payingguest.Adapter.MyPGListRAdapter;
import com.geekcoders.payingguest.Adapter.PGListAdapter;
import com.geekcoders.payingguest.Adapter.PGListRAdapter;
import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.geekcoders.payingguest.Utils.Dialog;
import com.geekcoders.payingguest.Utils.Fonts;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyPGActivity extends AppCompatActivity {

    private ArrayList<PGObject> arrayList;
    private PGListAdapter adapter;
    private RecyclerView recyclerView;
    private static PGListRAdapter pgListRAdapter;
    private TextView tvActionBar;
    private String TAG = "Permission";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pg);
        Parse.initialize(MyPGActivity.this);
        getSupportActionBar().hide();
        Constant.mcontext=MyPGActivity.this;
        Initiliztion();
        PgList();

        registerForContextMenu(recyclerView);
    }

    private void Initiliztion() {

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        tvActionBar = (TextView)findViewById(R.id.tv_actionBar);
        Fonts.setRegularFont(MyPGActivity.this,tvActionBar);
    }


    public void PgList() {
        Dialog.showDialog(MyPGActivity.this);
        arrayList = new ArrayList<>();

        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("PGDetail");


//
//        if (band_type == 0) {
//            bandQuery.whereEqualTo("creator", ParseUser.getCurrentUser());
//        } else {
//            bandQuery.whereContainedIn("bandMembers", Collections.singletonList(ParseUser.getCurrentUser()));
//        }
        bandQuery.whereEqualTo("userId", Constant.getValueForKeyString("userId"));
        bandQuery.orderByDescending("createdAt");
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
                        String image = objects.get(i).getString("image");
                        Date date = objects.get(i).getCreatedAt();
                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        String fdate = df.format(date);
                        obj.setDate(fdate);
                        obj.setImage(image);
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


//                        ParseQuery<ParseObject> imgQuery = ParseQuery.getQuery("Images");
//                        imgQuery.whereEqualTo("sourceId", objects.get(i).getObjectId());
//                        imgQuery.findInBackground(new FindCallback<ParseObject>() {
//                            @Override
//                            public void done(List<ParseObject> objects, ParseException e) {
//                                if (e == null && objects.size() != 0) {
//                                    final ParseObject object = objects.get(0);
//                                    final ParseFile fileObject = (ParseFile) object.get("file");
//                                    fileObject.getDataInBackground(new GetDataCallback() {
//                                        @Override
//                                        public void done(byte[] data, ParseException e) {
//                                            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
//                                            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
//                                           // objrcband.setImage(bitmap);
//                                            obj.setImage(bitmap);
//                                            pgListRAdapter.notifyDataSetChanged();
//                                        }
//                                    });
//                                }
//                            }
//                        });
                        arrayList.add(obj);

                    }

//                    adapter = new PGListAdapter(PGListActivity.this, arrayList);
                    pgListRAdapter = new PGListRAdapter(MyPGActivity.this,arrayList);
                    try {
                        recyclerView.setAdapter(pgListRAdapter);
                    }catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }

                   Dialog.closeDialog();

                } else {
                    Dialog.closeDialog();

                }


            }
        });

    }

    public static void notifydata(){

        pgListRAdapter.notifyDataSetChanged();
    }

//    /*@Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
//    {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        //menu.setHeaderTitle("Select The Action");
//        menu.add(0, v.getId(), 0, "Delete");//groupId, itemId, order, title
//       // menu.add(0, v.getId(), 0, "SMS");
//    }*/
//    @Override
//    public boolean onContextItemSelected(MenuItem item){
//
//
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        int index = info.position;
//        PGObject object=arrayList.get(index);
//        if(item.getTitle()=="Delete"){
//            deletePg(object.getObjectId());
//        }
////        else if(item.getTitle()=="SMS"){
////            Toast.makeText(getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();
////        }
//        else{
//            return false;
//        }
//        return true;
//    }

/*    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position = ((PGListRAdapter)getAdapter()).getPosition();
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.ctx_menu_remove_backup:
                // do your stuff
                break;
            case R.id.ctx_menu_restore_backup:
                // do your stuff
                break;
        }
        return super.onContextItemSelected(item);
    }*/




}
