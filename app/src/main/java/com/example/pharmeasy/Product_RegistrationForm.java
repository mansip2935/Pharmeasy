package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class Product_RegistrationForm extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    ImageButton productimage;
    TextInputLayout productname,manufacturecompanydetails,productdescription,productprice,quantity;
    String product_name,product_manufacture,product_description,product_price,product_displaydate,product_quantity,product_category;

    private TextInputLayout displaydate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Button addproduct_submit;


        String[] categories = {"Covid Essentials","Home care","Baby care","Devices","Women Care","Nutrition and Healthcare Suppliments"};
        Spinner spinner;
    private Object AdapterView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__registration_form);
        productimage=(ImageButton)findViewById(R.id.image_upload) ;
        productname=(TextInputLayout)findViewById(R.id.productname);
        manufacturecompanydetails=(TextInputLayout)findViewById(R.id.manufacturecompanydetails);
        productdescription=(TextInputLayout)findViewById(R.id.productdescription);
        productprice=(TextInputLayout)findViewById(R.id.productdescription);
        quantity=(TextInputLayout)findViewById(R.id.quantity);
        displaydate = (TextInputLayout)findViewById(R.id.selectdate);
        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        addproduct_submit=(Button) findViewById(R.id.addproduct_submit);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);


        addproduct_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 product_name = productname.getEditText().getText().toString().trim();
              /*  product_manufacture = manufacturecompanydetails.getEditText().getText().toString().trim();
                 product_description =  productdescription.getEditText().getText().toString().trim();
                 product_price = productprice.getEditText().getText().toString().trim();
                 product_displaydate =  displaydate.getEditText().getText().toString().trim();
                 product_quantity = quantity.getEditText().getText().toString().trim();
                 product_category= spinner.getSelectedItem().toString().trim();*/
                Log.d("onClick: ",product_name +"  ");
Toast.makeText(getApplicationContext(),"    "+productname,Toast.LENGTH_LONG).show();
            }
        });


        displaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(Product_RegistrationForm.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }


        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener(){
        @Override
            public void  onDateSet(DatePicker datePicker, int year, int month, int day){
            month =month+1;
            Log.d(TAG,"onDateSet: mm/dd/yy" + month + "/" + day + "/" + year);
            String date= month + "/" + day + "/" +year;
            displaydate.getEditText().setText(date);

        }
        };
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(),categories[position],Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }





}