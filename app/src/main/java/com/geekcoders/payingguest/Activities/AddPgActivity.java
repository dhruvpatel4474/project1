package com.geekcoders.payingguest.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPgActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtTitle;
    EditText edtPrice, edtNumber, edtAddress, edtDescription;
    Spinner spinnerCity;
    Spinner spinnerCategory;
    Button btnAddPG;
    private ParseObject objPGParse;
    private Bitmap imageBit;
    private ParseFile parseFile;
    private ImageView imgPG;
    private int PICK_IMAGE_REQUEST = 1;
    private String TAG = "Permission";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pg);
        initialize();
        getSupportActionBar().hide();
        Parse.initialize(AddPgActivity.this);
        Constant.mcontext = AddPgActivity.this;
    }


    public void CreatePostOnServer() throws Exception {
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
        objPGParse = new ParseObject("PGDetail");
        objPGParse.put("title", edtTitle.getText().toString());
        objPGParse.put("price", edtPrice.getText().toString());// int
        Category city = (Category) spinnerCity.getSelectedItem();
        objPGParse.put("city", city.getName().toString());
        objPGParse.put("description", edtDescription.getText().toString());
        objPGParse.put("userId", Constant.getValueForKeyString("userId"));
        objPGParse.put("address", edtAddress.getText().toString());
        objPGParse.put("number", edtNumber.getText().toString());
        objPGParse.put("userName", Constant.getValueForKeyString("name"));
        Category category = (Category) spinnerCategory.getSelectedItem();
        objPGParse.put("categoryId", category.getObjectId().toString());

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
        final ArrayList<Category> catList = new ArrayList<>();
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
                    ArrayAdapter arrayAdapter = new ArrayAdapter(AddPgActivity.this, android.R.layout.simple_list_item_1, catList);
                    spinnerCategory.setAdapter(arrayAdapter);


                }
            }
        });


    }

    public void CityList() {
        final ArrayList<Category> catList = new ArrayList<>();
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
                    ArrayAdapter arrayAdapter = new ArrayAdapter(AddPgActivity.this, android.R.layout.simple_list_item_1, catList);
                    spinnerCity.setAdapter(arrayAdapter);

                }
            }
        });


    }

    public boolean validate() {
        if (edtTitle.getText().toString().trim().equals("")) {
            edtTitle.setError("Please Enter Title");
            return false;
        } else if (edtAddress.getText().toString().trim().equals("")) {
            edtAddress.setError("Please Enter Address");
            return false;
        } else if (edtDescription.getText().toString().trim().equals("")) {
            edtDescription.setError("Please Enter Description");
            return false;
        } else if (edtNumber.getText().toString().trim().equals("")) {
            edtNumber.setError("Please Enter Number");
            return false;
        } else if (edtPrice.getText().toString().trim().equals("")) {
            edtPrice.setError("Please Enter Price");
            return false;
        } else {
            return true;
        }
    }

    public void initialize() {
        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtPrice = (EditText) findViewById(R.id.edt_price);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        edtAddress = (EditText) findViewById(R.id.edt_address);
        edtDescription = (EditText) findViewById(R.id.edt_description);
        spinnerCity = (Spinner) findViewById(R.id.spinner_city);
        spinnerCategory = (Spinner) findViewById(R.id.spinner_category);
        btnAddPG = (Button) findViewById(R.id.btnAddPG);
        imgPG = (ImageView) findViewById(R.id.img_adddpg);

        btnAddPG.setOnClickListener(this);
        imgPG.setOnClickListener(this);
        CategoryList();
        CityList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddPG:
                if (validate()) {
                    try {
                        CreatePostOnServer();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.img_adddpg:
                readImage();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                imgPG.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readImage()
    {
        if (isStoragePermissionGranted())
        {
            Intent intent = new Intent();
            // Show only images, no videos or anything else
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            // Always show the chooser (if there are multiple options available)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            readImage();
        }
    }
}

