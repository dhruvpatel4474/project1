package com.geekcoders.payingguest.Activities;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.geekcoders.payingguest.Adapter.CategoryAdapter;
import com.geekcoders.payingguest.Adapter.CategoryRAdapter;
import com.geekcoders.payingguest.Adapter.PGListRAdapter;
import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

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
    private LinearLayout lineLayMyPG,lineLayAddWorker,lineLayViewWorker;
    private ParseFile parseFile;
    private SliderLayout mDemoSlider;
    private ImageView logoutBtn;

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

            PgList();

//            BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider1);
//            List<Banner> banners=new ArrayList<>();
//            //add banner using image url
//            banners.add(new RemoteBanner("http://akomgreen.com/wp-content/themes/Divi/images/logo.png"));
//            banners.add(new RemoteBanner("http://akomgreen.com/wp-content/themes/Divi/images/logo.png"));
//            banners.add(new RemoteBanner("http://akomgreen.com/wp-content/themes/Divi/images/logo.png"));
//            banners.add(new RemoteBanner("http://akomgreen.com/wp-content/themes/Divi/images/logo.png"));
//            //add banner using resource drawable
//           // banners.add(new DrawableBanner(R.drawable.cat_icon));
//            bannerSlider.setBanners(banners);
//
//          //
//            bannerSlider.setOnBannerClickListener(new OnBannerClickListener() {
//                @Override
//                public void onClick(int position) {
//                    Toast.makeText(HomeActivity.this, "Banner with position " + String.valueOf(position) + " clicked!", Toast.LENGTH_SHORT).show();
//                }
//            });


        }
    }

    public void intialize() {
        lineLayCategory = (LinearLayout) findViewById(R.id.lineLay_category);
        lineLayViewPayment = (LinearLayout) findViewById(R.id.lineLay_viewPayment);
        lineLayAddPG = (LinearLayout) findViewById(R.id.lineLay_addpg);
        lineLayViewHistory = (LinearLayout) findViewById(R.id.lineLay_history);
        lineLayAddInfo = (LinearLayout) findViewById(R.id.lineLay_addinfo);
        lineLayMyPG = (LinearLayout)findViewById(R.id.lineLay_myPg);
        lineLayAddWorker = (LinearLayout)findViewById(R.id.lineLay_addworker);
        lineLayViewWorker = (LinearLayout)findViewById(R.id.lineLay_viewWorker);
        logoutBtn = (ImageView)findViewById(R.id.logoutbtn);

        lineLayCategory.setOnClickListener(this);
        lineLayAddPG.setOnClickListener(this);
        lineLayViewPayment.setOnClickListener(this);
        lineLayViewHistory.setOnClickListener(this);
        lineLayAddInfo.setOnClickListener(this);
        lineLayMyPG.setOnClickListener(this);
        lineLayAddWorker.setOnClickListener(this);
        lineLayViewWorker.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
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
            case R.id.lineLay_myPg :
                startActivity(new Intent(HomeActivity.this,MyPGActivity.class));
                break;
            case R.id.lineLay_addworker :
                startActivity(new Intent(HomeActivity.this,AddWorkerActivity.class));
                break;
            case R.id.lineLay_viewWorker :
                startActivity(new Intent(HomeActivity.this,WorkerListActivity.class));
                break;

            case R.id.logoutbtn :
                Constant.setValueAndKeyBoolean("isLogin",false);
                ParseUser.logOut();
                Intent intent =new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finishAffinity();
                break;
        }
    }

    public void showAddOptionDialog() {
        dialogAddOption = new Dialog(this);
        dialogAddOption.onAttachedToWindow();
        dialogAddOption.setTitle("Select Option");
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
        dialogCategory.setTitle("Show Category");
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
        dialogAddCategory.setTitle("Add Category");
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
        final EditText catName = (EditText)dialogAddCategory.findViewById(R.id.edtCatName);
        LinearLayout lineLayAddCat = (LinearLayout)dialogAddCategory.findViewById(R.id.lineLay_addCategory);
        TextView tvAddCategory = (TextView)dialogAddCategory.findViewById(R.id.tv_addCategory);
        LinearLayout lineLayImage = (LinearLayout)dialogAddCategory.findViewById(R.id.lineLay_image);
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
            lineLayImage.setVisibility(View.VISIBLE);
            tvAddCategory.setText("Add Category");
            catName.setHint("Category Name");
        }
        else {
            imgAddCat.setVisibility(View.GONE);
            lineLayImage.setVisibility(View.GONE);
            tvAddCategory.setText("Add City");
            catName.setHint("City Name");
        }
        lineLayAddCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!catName.getText().toString().trim().equals("")) {
                    if (isCategory) {
                        //Add category call
                        UploadImageForPost(catName, imgAddCat);


                    } else {
                        //Add City Call
                        try {
                            AddCityServer(catName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    catName.setError("Please fill this field");
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



    public void CreatePostOnServer(String imagePath,EditText editText,ImageView imageView) throws Exception {

        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        imageBit=bitmap;

        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
        ParseObject objCat = new ParseObject("Category");
        objCat.put("title", editText.getText().toString());

        objCat.put("image", imagePath);
        objCat.setACL(acl);
        objCat.saveInBackground((new SaveCallback() {
            public void done(ParseException e) {
                // Handle success or failure here ...

                if (e == null) {


                    //loading.cancel();
                    Toast.makeText(HomeActivity.this, "Successfully added", Toast.LENGTH_LONG).show();
                   // finish();
                    if (dialogAddCategory.isShowing()){
                        dialogAddCategory.dismiss();
                    }

                } else

                {
//                loading.cancel();
                    Toast.makeText(HomeActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }));
    }


    public void UploadImageForPost(final EditText editText, final ImageView imageView) {

        if (IsLogoUploaded) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = imageBit;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] image = stream.toByteArray();
            Random r = new Random();
            int i1 = (r.nextInt(80));
            Random r2 = new Random();
            int i2 = (r2.nextInt(99));
            Random r3 = new Random();
            int i3 = (r3.nextInt(80));
            String name = i3 + i1 + i2 + ".png";
            parseFile = new ParseFile(name, image, ".png");

            parseFile.saveInBackground(new SaveCallback() {
                                           @Override
                                           public void done(ParseException e) {
                                               if (e == null) {
                                                   try {
                                                       CreatePostOnServer(parseFile.getUrl(),editText,imageView);
                                                   } catch (Exception ex) {
                                                       ex.printStackTrace();
                                                   }


                                               } else {

                                                   Toast.makeText(HomeActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                                   //  loading.cancel();
                                               }
                                           }
                                       }

            );

        } else {

            try {
                CreatePostOnServer("null",editText,imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }





    public void AddCityServer(final EditText editText) throws Exception {

        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
       ParseObject objCity = new ParseObject("Location");
        objCity.put("title", editText.getText().toString());
        objCity.setACL(acl);
        objCity.saveInBackground((new SaveCallback() {
            public void done(ParseException e) {
                // Handle success or failure here ...

                if (e == null) {


                    //loading.cancel();
                    Toast.makeText(HomeActivity.this, "Successfully added", Toast.LENGTH_LONG).show();
                    editText.setText("");
                    //finish();
                    if (dialogAddCategory.isShowing()){
                        dialogAddCategory.dismiss();
                    }

                } else

                {
//                loading.cancel();
                    Toast.makeText(HomeActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }));
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void slider(ArrayList<PGObject> arrayList1){

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);


        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put(arrayList1.get(0).getName(), arrayList1.get(0).getImage());
        url_maps.put(arrayList1.get(1).getName(), arrayList1.get(1).getImage());
        url_maps.put(arrayList1.get(2).getName(), arrayList1.get(2).getImage());
        url_maps.put(arrayList1.get(3).getName(), arrayList1.get(3).getImage());
        url_maps.put(arrayList1.get(4).getName(), arrayList1.get(4).getImage());

//        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Hannibal",R.drawable.hannibal);
//        file_maps.put("Big Bang Theory",R.drawable.bigbang);
//        file_maps.put("House of Cards",R.drawable.house);
//        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
//        ListView l = (ListView)findViewById(R.id.transformers);
//        l.setAdapter(new TransformerAdapter(this));
//        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
//                Toast.makeText(MainActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }


    public void PgList() {
         final ArrayList<PGObject> arrayList1 = new ArrayList<>();

        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("PGDetail");
        com.geekcoders.payingguest.Utils.Dialog.showDialog(HomeActivity.this);
        bandQuery.orderByDescending("createdAt");
        bandQuery.whereNotEqualTo("image","null");
        bandQuery.setLimit(5);
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
                        arrayList1.add(obj);

                    }

                    slider(arrayList1);
//                    adapter = new PGListAdapter(PGListActivity.this, arrayList);


                    com.geekcoders.payingguest.Utils.Dialog.closeDialog();

                } else {
                    com.geekcoders.payingguest.Utils.Dialog.closeDialog();


                }


            }
        });

    }
}
