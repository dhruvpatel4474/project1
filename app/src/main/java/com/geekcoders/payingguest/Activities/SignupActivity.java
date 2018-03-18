package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.geekcoders.payingguest.R;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    private EditText fnameEdt,passwordEdt,emailEdt,lnameEdt;
    private Button signupBtn;
    private Spinner citySpnr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Parse.initialize(this);
        Initilize();





        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name= fnameEdt.getText().toString()+ lnameEdt.getText().toString();
                final String password= passwordEdt.getText().toString();
                final String email= emailEdt.getText().toString();
                if (fnameEdt.getText().toString().trim().equals("")){

                    fnameEdt.setError("Please enter First Name");
                }else if (lnameEdt.getText().toString().trim().equals("")){

                    lnameEdt.setError("Please enter Last Name");
                } else if (passwordEdt.getText().toString().trim().equals("")){

                    passwordEdt.setError("Please enter Password");
                }else if (emailEdt.getText().toString().trim().equals("")){

                    emailEdt.setError("Please enter Email");
                } else {


                    Signup(name,email, password);
                }
            }
        });


    }
    private void Initilize(){

        fnameEdt=(EditText)findViewById(R.id.edtFname);
        lnameEdt = (EditText)findViewById(R.id.edtLname);
        passwordEdt=(EditText)findViewById(R.id.edtPassword);
        emailEdt=(EditText)findViewById(R.id.edtEmail);
        signupBtn=(Button)findViewById(R.id.btnRegistration);
        citySpnr = (Spinner)findViewById(R.id.spinner_city);
        setSpinner();
    }

    public void Signup(String name, final String email, String password){


        final ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);

// other fields can be set just like with ParseObject
        user.put("name", name);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {

                    Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    String message= e.getLocalizedMessage();
                    Toast.makeText(SignupActivity.this,message,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setSpinner()
    {

    }
}
