package com.geekcoders.payingguest.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdt,passwordEdt;
    private Button loginBtn;
    private Context mContext;
    private TextView signupBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        Parse.initialize(this);
        getSupportActionBar().hide();
        mContext= LoginActivity.this;
        Initilize();




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username= usernameEdt.getText().toString();
                final String password= passwordEdt.getText().toString();
                if (username.trim().equals("")){

                 usernameEdt.setError("Please enter username");
                } else if (password.trim().equals("")){

                    passwordEdt.setError("Please enter password");
                } else {


                    Login(username, password);
                }
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);

            }
        });

    }

    private void Initilize(){

        usernameEdt=(EditText)findViewById(R.id.edtEmail);
        passwordEdt=(EditText)findViewById(R.id.edtPassword);
        loginBtn=(Button) findViewById(R.id.btnLogin);
        signupBtn=(TextView)findViewById(R.id.tv_signUp);


    }
    public void Login(final String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    String userId=user.getObjectId();
                    String name=user.getString("name");
                    Constant.mcontext=LoginActivity.this;
                    Constant.setValueAndKeyString("userId",userId);
                    Constant.setValueAndKeyString("email",username);
                    Constant.setValueAndKeyString("name",name);
                    Constant.setValueAndKeyBoolean("isLogin",true);


                    // Store app language and version
                    ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                    installation.put("userId",userId);
                    installation.saveInBackground();

                    ParsePush.subscribeInBackground(userId);
                   Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    String message= e.getLocalizedMessage();
                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
