package com.geekcoders.payingguest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekcoders.payingguest.Adapter.CommentAdapter;
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

import java.util.ArrayList;
import java.util.List;

public class PgDetailActivity extends AppCompatActivity {

    private TextView title;
    private EditText addCommentEdt;
    private Button addCommentBtn;
    private String PGid;
    ArrayList<Comment> commentList;
    private CommentAdapter adpt;
    private ListView commnetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_detail);
        Parse.initialize(PgDetailActivity.this);
        Initiliztion();

        PGObject object = Constant.pgObject;
        title.setText(object.getName().toString());
        PGid = object.getObjectId();



        GetCommentList();


        addCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addCommentEdt.getText().toString().trim().equals("")) {
                    addCommentEdt.setError("Please enter feedback");
                } else {
//                    dialog.show();
                    ParseObject commentData = new ParseObject("Feedback");
                   // commentData.put("post", objpost);

                    commentData.put("comment", addCommentEdt.getText().toString());
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



    }

    private void Initiliztion() {

        title = (TextView) findViewById(R.id.title);
        addCommentEdt = (EditText) findViewById(R.id.addCommentEdt);
        addCommentBtn = (Button) findViewById(R.id.addCommentBtn);
        commnetList = (ListView) findViewById(R.id.commnetList);

    }

//    private void GetPGData(){
//
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
//        query.getInBackground(Constant.PGId, new GetCallback<ParseObject>() {
//            public void done(ParseObject object, ParseException e) {
//                if (e == null) {
//                    String title = object.getString("title");
//                } else {
//                    // something went wrong
//                }
//            }
//        });
//
//    }


    private void AddComment() {


    }

    public void GetCommentList() {

        commentList=new ArrayList<>();
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Feedback");
        query.whereEqualTo("PGId", PGid);
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


                        commentList.add(obj);
                    }
                    adpt = new CommentAdapter(PgDetailActivity.this, commentList);
                    commnetList.setAdapter(adpt);
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
