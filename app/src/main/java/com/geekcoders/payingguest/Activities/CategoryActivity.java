package com.geekcoders.payingguest.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geekcoders.payingguest.Adapter.CategoryAdapter;
import com.geekcoders.payingguest.Adapter.CategoryRAdapter;
import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.geekcoders.payingguest.Utils.Dialog;
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
    private ArrayList<Category> arrayList;
    private CategoryAdapter adapter;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Parse.initialize(CategoryActivity.this);
        getSupportActionBar().hide();

        Constant.mcontext=CategoryActivity.this;
        Initiliztion();
        CategoryList();

    }

    private void Initiliztion() {

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));


    }


    public void CategoryList() {
        arrayList = new ArrayList<>();
        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Category");
        Dialog.showDialog(CategoryActivity.this);

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
                        String image = objects.get(i).getString("image");
                        String objectId = objects.get(i).getObjectId();
                        obj.setName(name);
                        obj.setObjectId(objectId);
                        obj.setImg(image);


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

                    //adapter = new CategoryAdapter(CategoryActivity.this, arrayList);
                    CategoryRAdapter categoryRAdapter = new CategoryRAdapter(CategoryActivity.this,arrayList,false);
                    recyclerView.setAdapter(categoryRAdapter);
                    Dialog.closeDialog();

                } else {
                    Dialog.closeDialog();


                }


            }
        });

    }


}
