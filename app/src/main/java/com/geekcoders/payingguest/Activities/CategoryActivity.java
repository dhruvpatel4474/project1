package com.geekcoders.payingguest.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.geekcoders.payingguest.Adapter.CategoryAdapter;
import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
private ListView listView;
private ArrayList<Category> arrayList;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Parse.initialize(CategoryActivity.this);
        Initiliztion();
        CategoryList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Constant.categoryId=arrayList.get(i).getObjectId();
                Intent intent=new Intent(CategoryActivity.this,PGListActivity.class);
                startActivity(intent);
            }
        });


    }

    private void Initiliztion(){

        listView=(ListView)findViewById(R.id.listView);

    }



    public void CategoryList() {
        arrayList=new ArrayList<>();
        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Category");

        final ProgressDialog dialog = new ProgressDialog(CategoryActivity.this);
        dialog.setMessage("Please wait");
        dialog.show();

//
//        if (band_type == 0) {
//            bandQuery.whereEqualTo("creator", ParseUser.getCurrentUser());
//        } else {
//            bandQuery.whereContainedIn("bandMembers", Collections.singletonList(ParseUser.getCurrentUser()));
//        }

        bandQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects != null && objects.size() > 0) {


                    for (int i = 0; i < objects.size(); i++) {

                        final Category obj = new Category();
                        String name = objects.get(i).getString("title");
                        String objectId = objects.get(i).getObjectId();
                        obj.setName(name);
                        obj.setObjectId(objectId);


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

                    adapter = new CategoryAdapter(CategoryActivity.this, arrayList);
                    listView.setAdapter(adapter);
                    dialog.cancel();

                } else {
                    dialog.cancel();


                }


            }
        });

    }


}
