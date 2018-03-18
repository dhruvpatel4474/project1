package com.geekcoders.payingguest.Activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.geekcoders.payingguest.Adapter.CategoryAdapter;
import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AddPgActivity extends AppCompatActivity {

    private ParseObject objPGParse;
    private Bitmap imageBit;
    private ParseFile parseFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pg);
        Parse.initialize(AddPgActivity.this);
    }


    public void CreatePostOnServer() {
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
        objPGParse = new ParseObject("PGDetail");
//        objPGParse.put("title", edtPost.getText().toString());
//        objPGParse.put("price", edtPost.getText().toString());// int
//        objPGParse.put("city", edtPost.getText().toString());
//        objPGParse.put("description", edtPost.getText().toString());
//        objPGParse.put("userId", edtPost.getText().toString());
//        objPGParse.put("address", edtPost.getText().toString());
//        objPGParse.put("number", edtPost.getText().toString());
//        objPGParse.put("number", edtPost.getText().toString());
//        objPGParse.put("message", edtPost.getText().toString());
//        objPGParse.put("userName", edtPost.getText().toString());
//        objPGParse.put("categoryId", objBand);

        objPGParse.setACL(acl);
        objPGParse.saveInBackground((new SaveCallback() {
            public void done(ParseException e) {
                // Handle success or failure here ...

                if (e == null) {

                    if (imageBit != null) {
                        UploadImageForPost(objPGParse.getObjectId());
                    } else {
                        //loading.cancel();
                        Toast.makeText(AddPgActivity.this, "Successfully added", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else

                {
//                loading.cancel();
                    Toast.makeText(AddPgActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }));
    }


    public void UploadImageForPost(final String sourceId) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = imageBit;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] image = stream.toByteArray();
        parseFile = new ParseFile(image);

        parseFile.saveInBackground(new SaveCallback() {
                                       @Override
                                       public void done(ParseException e) {
                                           if (e == null) {
                                               ParseACL acl = new ParseACL();
                                               acl.setPublicReadAccess(true);
                                               acl.setPublicWriteAccess(true);
                                               ParseObject objRcImage = new ParseObject("Images");
                                               objRcImage.put("sourceId", sourceId);
                                               objRcImage.put("file", parseFile);
                                               objRcImage.put("url", parseFile.getUrl());
                                               objRcImage.setACL(acl);
                                               objRcImage.saveInBackground((new SaveCallback() {
                                                   public void done(ParseException e) {
                                                       // Handle success or failure here ...
                                                       if (e == null) {

                                                           Toast.makeText(AddPgActivity.this, "Successfully added", Toast.LENGTH_LONG).show();
//                                                               loading.cancel();
                                                           finish();

                                                       }
                                                   }
                                               }
                                               ));

                                           } else {

                                               Toast.makeText(AddPgActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                               //  loading.cancel();
                                           }
                                       }
                                   }

        );


    }

    public void CategoryList() {
       final ArrayList<Category> catList=new ArrayList<>();
        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Category");
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
                        catList.add(obj);
                    }
                   // returnList(catList);

                }
            }
        });


    }

    public void CityList() {
        final ArrayList<Category> catList=new ArrayList<>();
        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Location");
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
                        catList.add(obj);
                    }
                    // returnList(catList);

                }
            }
        });


    }


}

