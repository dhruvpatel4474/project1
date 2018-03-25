package com.geekcoders.payingguest.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddWorkerActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imgWorker;
    private EditText edtFname,edtLname,edtWorkerType,edtNumber,edtDescription,edtPrice;
    private Spinner spnrCity;
    private Button btnAddWorker;
    private String TAG = "Permission";
    private int PICK_IMAGE_REQUEST = 1;
    private boolean IsLogoUploaded = false;
    private Bitmap imageBit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);
        getSupportActionBar().hide();
        initialize();

    }

    public void initialize()
    {
        imgWorker = (ImageView)findViewById(R.id.img_worker);
        edtFname = (EditText)findViewById(R.id.edtFname);
        edtLname = (EditText)findViewById(R.id.edtLname);
        edtWorkerType = (EditText)findViewById(R.id.edtWorkerType);
        edtNumber = (EditText)findViewById(R.id.edtNumber);
        edtDescription = (EditText)findViewById(R.id.edtDescription);
        edtPrice = (EditText)findViewById(R.id.edtPrice);
        spnrCity = (Spinner)findViewById(R.id.spinner_city);
        btnAddWorker = (Button)findViewById(R.id.btnAddWorker);

        imgWorker.setOnClickListener(this);
        btnAddWorker.setOnClickListener(this);
        getCityList();
    }

    public void setSpinner(ArrayList<Category> cityList) {
        try {
            ArrayList<String> cityList1 = new ArrayList<>();
            if (cityList.size() > 0) {
                for (int i = 0; i < cityList.size(); i++) {
                    Category catObj = cityList.get(i);
                    cityList1.add(catObj.getName());
                }

                if (cityList1.size() > 0) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList1);
                    spnrCity.setAdapter(arrayAdapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCityList() {
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
                    // returnList(catList);
                    setSpinner(catList);
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnAddWorker :
                if (validate()) {
                    try {
                        UploadImageForPost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.img_worker :
                readImage();
                break;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                IsLogoUploaded=true;
                imageBit=bitmap;
                // Log.d(TAG, String.valueOf(bitmap));
                imgWorker.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validate()
    {
        if (edtFname.getText().toString().trim().equals(""))
        {
            edtFname.setError("Please Enter First Name");
            return false;
        }else if (edtLname.getText().toString().trim().equals(""))
        {
            edtLname.setError("Please Enter Last Name");
            return false;
        }else if (edtDescription.getText().toString().trim().equals(""))
        {
            edtDescription.setError("Please Enter Description");
            return false;
        }else if (edtNumber.getText().toString().trim().equals(""))
        {
            edtNumber.setError("Please Enter Number");
            return false;
        }
        else if (edtPrice.getText().toString().trim().equals(""))
        {
            edtPrice.setError("Please Enter Price");
            return false;
        }else if (edtWorkerType.getText().toString().trim().equals(""))
        {
            edtWorkerType.setError("Please Enter Type");
            return false;
        }else
        {
            return true;
        }
    }

    public void UploadImageForPost()
    {
        String name,price,number,type,city,description;

        name = edtFname.getText().toString() +" "+edtLname.getText().toString();
        price = edtPrice.getText().toString();
        number = edtNumber.getText().toString();
        type = edtWorkerType.getText().toString();
        city = ((Category)spnrCity.getSelectedItem()).getName();
        description = edtDescription.getText().toString();
    }
}
