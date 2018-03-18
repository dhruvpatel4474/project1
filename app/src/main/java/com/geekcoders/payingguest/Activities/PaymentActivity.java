package com.geekcoders.payingguest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class PaymentActivity extends AppCompatActivity {
    private String recieverId;
    private int price;
    public ParseObject pgparseObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        price = Constant.price;
        recieverId= Constant.recieverId;
        pgparseObject=Constant.PGParseObject;

    }



//    public void AddPayment() {
//        ParseACL acl = new ParseACL();
//        acl.setPublicReadAccess(true);
//        acl.setPublicWriteAccess(true);
//        objPGParse = new ParseObject("PGDetail");
////        objPGParse.put("title", edtPost.getText().toString());
////        objPGParse.put("price", edtPost.getText().toString());// int
////        objPGParse.put("city", edtPost.getText().toString());
////        objPGParse.put("description", edtPost.getText().toString());
////        objPGParse.put("userId", edtPost.getText().toString());
////        objPGParse.put("address", edtPost.getText().toString());
////        objPGParse.put("number", edtPost.getText().toString());
////        objPGParse.put("number", edtPost.getText().toString());
////        objPGParse.put("message", edtPost.getText().toString());
////        objPGParse.put("userName", edtPost.getText().toString());
////        objPGParse.put("categoryId", objBand);
//
//        objPGParse.setACL(acl);
//        objPGParse.saveInBackground((new SaveCallback() {
//            public void done(ParseException e) {
//                // Handle success or failure here ...
//
//                if (e == null) {
//
//                    if (imageBit != null) {
//                        UploadImageForPost(objPGParse.getObjectId());
//                    } else {
//                        //loading.cancel();
//                        Toast.makeText(AddPgActivity.this, "Successfully added", Toast.LENGTH_LONG).show();
//                        finish();
//                    }
//                } else
//
//                {
////                loading.cancel();
//                    Toast.makeText(AddPgActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        }));
//    }
}
