package com.geekcoders.payingguest.Activities;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekcoders.payingguest.Adapter.CategoryAdapter;
import com.geekcoders.payingguest.Adapter.CategoryRAdapter;
import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.PushService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout lineLayCategory;
    private LinearLayout lineLayViewPayment, lineLayAddPG, lineLayViewHistory;
    private LinearLayout lineLayAddInfo;
    private Dialog dialogAddOption;
    private ArrayList<Category> arrayList;
    private Dialog dialogCategory;
    private RecyclerView recylCategory;
    private Dialog dialogAddCategory;
    private String TAG = "Permission";
    private Bitmap imageBit;
    private int PICK_IMAGE_REQUEST = 1;
    private boolean IsLogoUploaded = false;
    private ImageView imgAddCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        Parse.initialize(HomeActivity.this);

        intialize();

        Constant.mcontext = HomeActivity.this;
        if (!Constant.getValueForKeyBoolean("isLogin")) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
//            ParseUser user = ParseUser.getCurrentUser();
//            Log.d("log", "d");
//        if (user != null) {
//
//        }
            ParseInstallation.getCurrentInstallation().saveInBackground();

            // ParseQuery pushQuery = ParseInstallation.getQuery();


// Send push notification to query
//        ParsePush push = new ParsePush();
//       push.setChannel("NuDYCP0HdC");
//        push.setMessage("Willie Hayes injured by own pop fly.");
//        push.sendInBackground();


//                    ParseQuery searchquery = ParseQuery.getQuery("User");
//        searchquery.whereEqualTo("objectId", "NuDYCP0HdC");


            // Create our Installation query
//        ParseQuery pushQuery = ParseInstallation.getQuery();
//        pushQuery.whereEqualTo("userId","NuDYCP0HdC" );

// Send push notification to query
//        ParsePush push = new ParsePush();
//        push.setChannel("NuDYCP0HdC");
//        push.setMessage("test");
//        push.sendInBackground();


        }
    }

    public void intialize() {
        lineLayCategory = (LinearLayout) findViewById(R.id.lineLay_category);
        lineLayViewPayment = (LinearLayout) findViewById(R.id.lineLay_viewPayment);
        lineLayAddPG = (LinearLayout) findViewById(R.id.lineLay_addpg);
        lineLayViewHistory = (LinearLayout) findViewById(R.id.lineLay_history);
        lineLayAddInfo = (LinearLayout) findViewById(R.id.lineLay_addinfo);
        lineLayCategory.setOnClickListener(this);
        lineLayAddPG.setOnClickListener(this);
        lineLayViewPayment.setOnClickListener(this);
        lineLayViewHistory.setOnClickListener(this);
        lineLayAddInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lineLay_category:
                startActivity(new Intent(HomeActivity.this, CategoryActivity.class));
                break;

            case R.id.lineLay_viewPayment:
                startActivity(new Intent(HomeActivity.this, ViewPaymentActivity.class));
                break;

            case R.id.lineLay_addpg:
                startActivity(new Intent(HomeActivity.this, AddPgActivity.class));
                break;
            case R.id.lineLay_history:
                startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
                break;
            case R.id.lineLay_addinfo:
                showAddOptionDialog();
                break;
        }
    }

    public void showAddOptionDialog() {
        dialogAddOption = new Dialog(this);
        dialogAddOption.onAttachedToWindow();
        dialogAddOption.setTitle("Your NearBy Location");
        dialogAddOption.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddOption.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAddOption.setContentView(R.layout.dialog_option);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogAddOption.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogAddOption.getWindow().setAttributes(lp);

        Window window = dialogAddOption.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.x = 10;   //x position
        wlp.y = 10;
        window.setAttributes(wlp);

        ImageView imgClose = (ImageView) dialogAddOption.findViewById(R.id.img_close);
        LinearLayout lineLayaddCity = (LinearLayout) dialogAddOption.findViewById(R.id.lineLay_addCity);
        LinearLayout lineLayAddCategory = (LinearLayout) dialogAddOption.findViewById(R.id.lineLay_addCategory);
        TextView tvAddCity = (TextView) dialogAddOption.findViewById(R.id.tv_addCity);
        TextView tvAddCategory = (TextView) dialogAddOption.findViewById(R.id.tv_addCategory);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddOption.cancel();
                dialogAddOption.dismiss();
            }
        });

        lineLayAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddOption.cancel();
                dialogAddOption.dismiss();
                showAddCategoryDialog(true);
            }
        });

        lineLayaddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddOption.cancel();
                dialogAddOption.dismiss();
                showAddCategoryDialog(false);
            }
        });
        dialogAddOption.show();
    }

    public void showCategoryDialog() {
        dialogCategory = new Dialog(this);
        dialogCategory.onAttachedToWindow();
        dialogCategory.setTitle("Your NearBy Location");
        dialogCategory.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCategory.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogCategory.setContentView(R.layout.dialog_categorylist);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogCategory.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogCategory.getWindow().setAttributes(lp);

        Window window = dialogCategory.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.x = 10;   //x position
        wlp.y = 10;
        window.setAttributes(wlp);

        ImageView imgClose = (ImageView) dialogCategory.findViewById(R.id.img_close);
        recylCategory = (RecyclerView) dialogCategory.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recylCategory.setLayoutManager(mLayoutManager);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCategory.cancel();
                dialogCategory.dismiss();
            }
        });
        dialogCategory.show();

        //CategoryList();

    }

    public void showAddCategoryDialog(final boolean isCategory) {
        dialogAddCategory = new Dialog(this);
        dialogAddCategory.onAttachedToWindow();
        dialogAddCategory.setTitle("Your NearBy Location");
        dialogAddCategory.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddCategory.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAddCategory.setContentView(R.layout.dialog_add_cat_city);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogAddCategory.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogAddCategory.getWindow().setAttributes(lp);

        Window window = dialogAddCategory.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.x = 10;   //x position
        wlp.y = 10;
        window.setAttributes(wlp);

        ImageView imgClose = (ImageView) dialogAddCategory.findViewById(R.id.img_close);
        imgAddCat = (ImageView)dialogAddCategory.findViewById(R.id.img_addCategory);
        EditText catName = (EditText)dialogAddCategory.findViewById(R.id.edtCatName);
        LinearLayout lineLayAddCat = (LinearLayout)dialogAddCategory.findViewById(R.id.lineLay_addCategory);
        TextView tvAddCategory = (TextView)dialogAddCategory.findViewById(R.id.tv_addCategory);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddCategory.cancel();
                dialogAddCategory.dismiss();
            }
        });

        imgAddCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCategory)
                {
                    readImage();
                }
            }
        });

        if (isCategory)
        {
            imgAddCat.setVisibility(View.VISIBLE);
            tvAddCategory.setText("Add Category");
            tvAddCategory.setHint("City Name");
        }
        else {
            imgAddCat.setVisibility(View.GONE);
            tvAddCategory.setText("Add City");
            tvAddCategory.setHint("Category Name");
        }
        lineLayAddCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCategory)
                {
                    //Add category call
                }
                else {
                    //Add City Call
                }
            }
        });
        dialogAddCategory.show();

        //CategoryList();

    }

    public void CategoryList() {
        arrayList = new ArrayList<>();
        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Category");

        bandQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects != null && objects.size() > 0) {


                    if (arrayList.size() > 0) {
                        arrayList.clear();
                        for (int i = 0; i < objects.size(); i++) {

                            final Category obj = new Category();
                            String name = objects.get(i).getString("title");
                            String image = objects.get(i).getString("image");
                            String objectId = objects.get(i).getObjectId();
                            obj.setName(name);
                            obj.setObjectId(objectId);
                            obj.setImg(image);

                            arrayList.add(obj);

                        }

                    } else {
                        for (int i = 0; i < objects.size(); i++) {

                            final Category obj = new Category();
                            String name = objects.get(i).getString("title");
                            String image = objects.get(i).getString("image");
                            String objectId = objects.get(i).getObjectId();
                            obj.setName(name);
                            obj.setObjectId(objectId);
                            obj.setImg(image);

                            arrayList.add(obj);

                        }
                    }

                    //adapter = new CategoryAdapter(CategoryActivity.this, arrayList);
                    try {
                        CategoryRAdapter categoryRAdapter = new CategoryRAdapter(HomeActivity.this, arrayList, true);
                        recylCategory.setAdapter(categoryRAdapter);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                } else {

                }
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                IsLogoUploaded=true;
                imageBit=bitmap;
                // Log.d(TAG, String.valueOf(bitmap));
                imgAddCat.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
