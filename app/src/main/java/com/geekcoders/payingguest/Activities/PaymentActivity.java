package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class PaymentActivity extends AppCompatActivity {
    private String recieverId;
    private String recieverName;
    private int price;
    public ParseObject pgparseObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Parse.initialize(PaymentActivity.this);
        Constant.mcontext=PaymentActivity.this;

        price = Constant.price;
        recieverId= Constant.recieverId;
        recieverName= Constant.recieverName;
        pgparseObject=Constant.PGParseObject;

    }



    public void AddPayment() {
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
        ParseObject parseObject = new ParseObject("Payment");
        parseObject.put("price", price);
        parseObject.put("recieverId", recieverId);
        parseObject.put("recieverName", recieverName);
        parseObject.put("senderId", Constant.getValueForKeyString("userId"));
        parseObject.put("senderName", Constant.getValueForKeyString("Name"));
        parseObject.put("PGDetail", pgparseObject);


        parseObject.setACL(acl);
        parseObject.saveInBackground((new SaveCallback() {
            public void done(ParseException e) {
                // Handle success or failure here ...

                if (e == null) {


                        //loading.cancel();
                        Toast.makeText(PaymentActivity.this, "Payment Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
                    startActivity(intent);
                        finishAffinity();

                } else

                {
//                loading.cancel();
                    Toast.makeText(PaymentActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }));
    }
}
