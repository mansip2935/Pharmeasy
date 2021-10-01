package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;

import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.InputStream;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class Product_RegistrationForm extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    ImageButton productimage;
    TextInputLayout productname,manufacturecompanydetails,productdescription,productprice,quantity,ep_Date;
    String product_name,product_manufacture,product_description,product_price,product_displaydate,product_quantity,product_category;
    Details_obj details_obj;
    private TextInputEditText displaydate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Button addproduct_submit;
    Uri filepath;
    Bitmap bitmap;
    String cat;
    static String imguri;
    String[] categories = {"Covid Essentials","Home care","Baby care","Devices","Women Care","Nutrition and Healthcare Suppliments"};
    Spinner spinner;
    String uid;

    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__registration_form);
        productimage=(ImageButton)findViewById(R.id.image_upload) ;
        productname=(TextInputLayout)findViewById(R.id.productname);
        manufacturecompanydetails=(TextInputLayout)findViewById(R.id.manufacturecompanydetails);
        productdescription=(TextInputLayout)findViewById(R.id.productdescription);
        productprice=(TextInputLayout)findViewById(R.id.productprice);
        quantity=(TextInputLayout)findViewById(R.id.quantity);
        displaydate = (TextInputEditText)findViewById(R.id.regi_expriy_data);
        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        addproduct_submit=(Button) findViewById(R.id.addproduct_submit);

        details_obj=new Details_obj();

        firebaseDatabase = FirebaseDatabase.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();



        productimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("product image in", "onClick: ");
                Intent gg=new Intent();
                gg.setAction(Intent.ACTION_GET_CONTENT);
                gg.setType("image/*");
                startActivityForResult(gg,1);
            }
        });

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);


        addproduct_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product_name = productname.getEditText().getText().toString().trim();
                product_manufacture = manufacturecompanydetails.getEditText().getText().toString().trim();
                product_description =  productdescription.getEditText().getText().toString().trim();
                product_price = productprice.getEditText().getText().toString().trim();
                product_displaydate =  displaydate.getText().toString().trim();
                product_quantity = quantity.getEditText().getText().toString().trim();
                product_category= cat;

                uploadtofirebase();
                details_obj.setName(product_name);
                details_obj.setAbout_manufacture(product_manufacture);
                details_obj.setProduct_description(product_description);
                details_obj.setProduct_price(product_price);
                details_obj.setProduct_quantity(product_quantity);
                details_obj.setExpiry_date(product_displaydate);
                details_obj.setImageURL(imguri);
                details_obj.setSid(uid);




                databaseReference = firebaseDatabase.getReference("product");

                databaseReference.child(product_category).child(product_name).setValue(details_obj);
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
                displaydate.setText(date);

            }
        };
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            filepath=data.getData();
            try
            {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                productimage.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(),categories[position],Toast.LENGTH_LONG).show();
        int i= (int) arg0.getItemIdAtPosition(position);
        cat=categories[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }




    private void uploadtofirebase() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference().child(product_name+filepath).child("img1");
        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imguri = uri.toString();

                                Log.d("onSuccess: ", "uri" +imguri);
                                details_obj.setImageURL(imguri);
                                databaseReference = firebaseDatabase.getReference("product");

                                databaseReference.child(product_category).child(product_name).setValue(details_obj);
                            }
                        });
                        Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        float percent = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        dialog.setMessage("Uploaded :" +  percent + " %");
                    }
                });
    }}

