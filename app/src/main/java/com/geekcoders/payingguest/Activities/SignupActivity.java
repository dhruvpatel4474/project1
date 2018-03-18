package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    private EditText nameEdt,passwordEdt,emailEdt;
    private Button signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Parse.initialize(this);
        Initilize();





        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name= nameEdt.getText().toString();
                final String password= passwordEdt.getText().toString();
                final String email= emailEdt.getText().toString();
                if (name.trim().equals("")){

                    nameEdt.setError("Please enter name");
                } else if (password.trim().equals("")){

                    passwordEdt.setError("Please enter password");
                }else if (email.trim().equals("")){

                    emailEdt.setError("Please enter email");
                } else {


                    Signup(name,email, password);
                }
            }
        });


    }
    private void Initilize(){

        nameEdt=(EditText)findViewById(R.id.name);
        passwordEdt=(EditText)findViewById(R.id.password);
        emailEdt=(EditText)findViewById(R.id.email);

        signupBtn=(Button)findViewById(R.id.Signup);


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
}
