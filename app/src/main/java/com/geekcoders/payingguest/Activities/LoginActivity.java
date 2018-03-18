package com.geekcoders.payingguest.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdt,passwordEdt;
    private Button loginBtn,signupBtn;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Parse.initialize(this);
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

        usernameEdt=(EditText)findViewById(R.id.email);
        passwordEdt=(EditText)findViewById(R.id.password);
        loginBtn=(Button) findViewById(R.id.login);
        signupBtn=(Button)findViewById(R.id.Signup);


    }
    public void Login(final String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    String userId=user.getObjectId();
                    Constant.mcontext=LoginActivity.this;
                    Constant.setValueAndKeyString("userId",userId);
                    Constant.setValueAndKeyString("email",username);
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
