package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekcoders.payingguest.Adapter.CommentAdapter;
import com.geekcoders.payingguest.Adapter.CommentRAdapter;
import com.geekcoders.payingguest.Objects.Comment;
import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PgDetailActivity extends AppCompatActivity {


    private EditText addCommentEdt;

    ArrayList<Comment> commentList;
    private CommentAdapter adpt;
    private PGObject object;
   int finalPrice;
    String recieverId;
    private LinearLayout lineLayPay;
    private LinearLayout lineLayAddComment;
    private RecyclerView recyclerView;
    private ImageView pgImg;
    private TextView pgName,pgCity,pgPrice,pgDate;
    private TextView pgDescription;
    private TextView pgAddress;
    private LinearLayout lineLayCall;
    private String number;
    private TextView pgNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_detail);
        Parse.initialize(PgDetailActivity.this);
        getSupportActionBar().hide();
        Constant.mcontext=PgDetailActivity.this;
        Initiliztion();
        PGDetail();

        lineLayAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addCommentEdt.getText().toString().trim().equals("")) {
                    addCommentEdt.setError("Please enter feedback");
                } else {
//                    dialog.show();
                    ParseObject commentData = new ParseObject("Feedback");
                    // commentData.put("post", objpost);

                    commentData.put("feedbackMessage", addCommentEdt.getText().toString());
                    commentData.put("userName", Constant.getValueForKeyString("name"));
                    commentData.put("userId", Constant.getValueForKeyString("userId"));
                    commentData.put("PGId", Constant.getValueForKeyString("PGid"));
                    commentData.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                addCommentEdt.setText("");
                                GetCommentList();
//                                if (dialog.isShowing()) {
//                                    dialog.dismiss();
//                                }
                            } else {
                                Toast.makeText(PgDetailActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                                if (dialog.isShowing()) {
//                                    dialog.dismiss();
//                                }
                            }
                        }
                    });


                }
            }
        });


        lineLayPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.price=finalPrice;
                Constant.recieverId=recieverId;
                Intent intent=new Intent(PgDetailActivity.this,PaymentActivity.class);
                startActivity(intent);

            }
        });

        lineLayCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });


    }

    private void Initiliztion() {
        pgImg = (ImageView)findViewById(R.id.img_pgdetail);
        pgName = (TextView)findViewById(R.id.tv_name_pgdetail);
        pgCity = (TextView)findViewById(R.id.tv_city_pgdetail);
        pgPrice = (TextView)findViewById(R.id.tv_price_pgdetail);
        pgDate = (TextView)findViewById(R.id.tv_date_pgdetail);
        pgDescription = (TextView)findViewById(R.id.tv_description_pgdetail);
        addCommentEdt = (EditText) findViewById(R.id.addCommentEdt);
        lineLayCall = (LinearLayout)findViewById(R.id.lineLay_call);
        lineLayAddComment = (LinearLayout) findViewById(R.id.lineLay_addcomment);
        lineLayPay = (LinearLayout)findViewById(R.id.lineLay_pay);
        pgAddress = (TextView)findViewById(R.id.tv_address_pgdetail);
        pgNumber = (TextView)findViewById(R.id.tv_number_pgdetail);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

    }

    public void PGDetail() {

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("PGDetail");
        query.whereEqualTo("objectId", Constant.getValueForKeyString("PGid"));
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(final ParseObject object, ParseException e) {

//                Date date = object.getCreatedAt();
//                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                String fdate = df.format(date);
                String title = object.getString("title");
                String image = object.getString("image");
                String objectId = object.getObjectId();
                int price = object.getInt("price");
                String city = object.getString("city");
                String description = object.getString("description");
                String userId = object.getString("userId");
                String address = object.getString("address");
                number = object.getString("number");
                String userName = object.getString("UserName");
                String categoryId = object.getString("categoryId");
                Date date = object.getCreatedAt();
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String fdate = df.format(date);
                recieverId=userId;
                finalPrice=price;
                Constant.PGParseObject=object;
                Constant.recieverName=userName;

                pgName.setText(title);
                pgCity.setText(city);
                pgDate.setText(fdate);
                pgPrice.setText(String.valueOf(price));
                pgDescription.setText(description);
                pgAddress.setText(address);
                Picasso.get()
                        .load(image)
                        .placeholder(R.drawable.place_holder)
                        .error(R.drawable.place_holder)
                        .into(pgImg);
                GetCommentList();


//                ParseQuery<ParseObject> imgQuery = ParseQuery.getQuery("RCPostAttachment");
//                imgQuery.whereEqualTo("post", object);
//                imgQuery.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> objects, ParseException e) {
//
//                        if (e == null && objects.size() != 0) {
//
//                            for (int i = 0; objects.size() > i; i++) {
//
//
//                                final Post obj = new Post();
//                                obj.setFiletype(objects.get(i).getString("filetype"));
//                                final ParseObject object = objects.get(i);
//                                final ParseFile fileObject = (ParseFile) object.get("file");
//                                obj.setParseFile(fileObject);
//                                fileObject.getDataInBackground(new GetDataCallback() {
//                                    @Override
//                                    public void done(byte[] data, ParseException e) {
//                                        obj.setBytedata(data);
//
//
//                                        if (obj.getFiletype().equals("image")) {
//                                            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
//                                            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
//                                            obj.setImageBit(bitmap);
//                                        }
//
//                                        adapter.notifyDataSetChanged();
////                                    adpt.notifyDataSetChanged();
//                                    }
//                                });
//
//                                arrayList.add(obj);
//                            }
//
//
//                            adapter = new GridImageAdapter(PostDetailActivity.this, R.layout.grid_row, arrayList, GetScreenWidht() / 3);
//                            gridview.setAdapter(adapter);
////                            videoGet(object);
////                            LoadImages();
//                            if (dialog.isShowing()) {
//                                dialog.dismiss();
//                            }
//                            GetCommentList();
//
//
//                        }
//
//                    }
//                });


            }
        });

    }


    private void AddComment() {


    }

    public void GetCommentList() {

        commentList = new ArrayList<>();
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Feedback");
        query.whereEqualTo("PGId", Constant.getValueForKeyString("PGid"));
        query.orderByDescending("createdAt");
        // query.include("user");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects.size() != 0) {

                    for (int i = 0; objects.size() > i; i++) {

                        final Comment obj = new Comment();
                        obj.setCommentMessage(objects.get(i).getString("feedbackMessage"));

                        obj.setUserId(objects.get(i).getString("userId"));
                        obj.setUserName(objects.get(i).getString("userName"));
                        obj.setPGId(objects.get(i).getString("PGId"));
                        obj.setObjectId(objects.get(i).getObjectId());
                        Date date = objects.get(i).getCreatedAt();
                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        String fdate = df.format(date);
                        obj.setDate(fdate);


                        commentList.add(obj);
                    }

                    CommentRAdapter commentRAdapter = new CommentRAdapter(PgDetailActivity.this,commentList);
                    recyclerView.setAdapter(commentRAdapter);
//                    dialog.cancel();
//                    dialog.dismiss();
                } else {
//                    dialog.cancel();
//                    dialog.dismiss();

                }
            }
        });

    }


}
