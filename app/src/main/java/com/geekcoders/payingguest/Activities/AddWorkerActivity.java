package com.geekcoders.payingguest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.geekcoders.payingguest.R;

public class AddWorkerActivity extends AppCompatActivity {

    private ImageView imgWorker;
    private EditText edtFname,edtLname,edtWorkerType,edtNumber,edtDescription,edtPrice;
    private Spinner spnrCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);
        initialize();

    }

    public void initialize()
    {
        imgWorker = (ImageView)findViewById(R.id.img_worker);
        edtFname = (EditText)findViewById(R.id.edtFname);
        edtLname = (EditText)findViewById(R.id.edtLname);
        edtWorkerType = (EditText)findViewById(R.id.edtWorkerType);
        edtNumber = (EditText)findViewById(R.id.edtNumber);
        edtDescription = (EditText)findViewById(R.id.edtDescription);
        edtPrice = (EditText)findViewById(R.id.edtPrice);
        spnrCity = (Spinner)findViewById(R.id.spinner_city);
    }
}
