package com.geekcoders.payingguest.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private EditText fnameEdt, passwordEdt, emailEdt, lnameEdt;
    private Button signupBtn;
    private Spinner citySpnr;
    private EditText numberEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Parse.initialize(this);
        Initilize();
        getSupportActionBar().hide();


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = fnameEdt.getText().toString() + lnameEdt.getText().toString();
                final String password = passwordEdt.getText().toString();
                final String email = emailEdt.getText().toString();
                String number = numberEdt.getText().toString();
                String city = citySpnr.getSelectedItem().toString();

                if (fnameEdt.getText().toString().trim().equals("")) {

                    fnameEdt.setError("Please enter First Name");
                } else if (lnameEdt.getText().toString().trim().equals("")) {

                    lnameEdt.setError("Please enter Last Name");
                } else if (passwordEdt.getText().toString().trim().equals("")) {

                    passwordEdt.setError("Please enter Password");
                } else if (emailEdt.getText().toString().trim().equals("")) {

                    emailEdt.setError("Please enter Email");
                } else if (numberEdt.getText().toString().trim().equals("")) {

                    emailEdt.setError("Please enter Contact Number");
                } else {


                    Signup(name, email, password, number, city);
                }
            }
        });


    }

    private void Initilize() {

        fnameEdt = (EditText) findViewById(R.id.edtFname);
        lnameEdt = (EditText) findViewById(R.id.edtLname);
        passwordEdt = (EditText) findViewById(R.id.edtPassword);
        emailEdt = (EditText) findViewById(R.id.edtEmail);
        signupBtn = (Button) findViewById(R.id.btnRegistration);
        citySpnr = (Spinner) findViewById(R.id.spinner_city);
        numberEdt = (EditText) findViewById(R.id.edtNumber);
        getCityList();
    }

    public void Signup(String name, final String email, String password, String number, String city) {


        final ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);

// other fields can be set just like with ParseObject
        user.put("name", name);

        user.put("number", number);
        user.put("city", city);



        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {

                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    String message = e.getLocalizedMessage();
                    Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setSpinner(ArrayList<Category> cityList) {
        try {
            ArrayList<String> cityList1 = new ArrayList<>();
            if (cityList.size() > 0) {
                for (int i = 0; i < cityList.size(); i++) {
                    Category catObj = cityList.get(i);
                    cityList1.add(catObj.getName());
                }

                if (cityList1.size() > 0) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList1);
                    citySpnr.setAdapter(arrayAdapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getCityList() {
        final ArrayList<Category> catList = new ArrayList<>();
        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Location");
        bandQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects != null && objects.size() > 0) {

                    for (int i = 0; i < objects.size(); i++) {

                        final Category obj = new Category();
                        String name = objects.get(i).getString("title");
                        String objectId = objects.get(i).getObjectId();
                        obj.setName(name);
                        obj.setObjectId(objectId);
                        catList.add(obj);
                    }
                    // returnList(catList);
                    setSpinner(catList);
                }
            }
        });


    }
}
