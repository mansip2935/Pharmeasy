package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Confirm_order extends AppCompatActivity {
    TextView nametx,manufacture,description,expiry_datetx;
    ImageView img;
    String name ,product_price,imageURL,about_manufacture,expiry_date,product_description,sid;
    String cust_name ,cust_lastname,cust_Address,cust_area,cust_city,cust_state,cust_pincode, phar_name,phar_last,phar_Address,phar_area,phar_city,phar_state,phar_pincode;
    Button cancel,order;
    String uid;
    FloatingActionButton add_cart_btn;
    DatabaseReference databaseReference,databaseReference2,databaseReference3,databaseReference4;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DataModel dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        nametx=(TextView) findViewById(R.id.order_name);
        description=(TextView) findViewById(R.id.order_Description);
        manufacture=(TextView) findViewById(R.id.order_Manufacture);
        expiry_datetx =(TextView) findViewById(R.id.order_Expiry_Date);
        add_cart_btn=(FloatingActionButton)findViewById(R.id.cart_fbtn);
        img=(ImageView)findViewById(R.id.order) ;
        cancel=(Button)findViewById(R.id.btncancel);
        order=(Button)findViewById(R.id.btnorder);



        getIntent().getExtras().get("position");
        name=getIntent().getExtras().get("name").toString();
        product_price=getIntent().getExtras().get("product_price").toString();
        imageURL=getIntent().getExtras().get("imageURL").toString();
        about_manufacture=getIntent().getExtras().get("manufacture").toString();
        expiry_date=getIntent().getExtras().get("expiry_date").toString();
        product_description=getIntent().getExtras().get("product_description").toString();
        sid=getIntent().getExtras().get("sid").toString();

        nametx.setText(name);
        manufacture.setText(about_manufacture);
        description.setText(product_description);
        expiry_datetx.setText(expiry_date);
        Glide.with(img.getContext()).load(imageURL).into(img);
        order.setText(product_price+"-/rs");

        dm=new DataModel();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference("Customer");
        databaseReference2=FirebaseDatabase.getInstance().getReference("Pharmacist");
        databaseReference4=FirebaseDatabase.getInstance().getReference("proudct");

/*databaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        name ,product_price
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
*/

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cust_name=snapshot.child(uid).child("First Name").getValue(String.class);
                cust_lastname=snapshot.child(uid).child("Last Name").getValue(String.class);
                cust_Address=snapshot.child(uid).child("Local Address").getValue(String.class);
                cust_area=snapshot.child(uid).child("area").getValue(String.class);
                cust_city=snapshot.child(uid).child("City").getValue(String.class);
                cust_state=snapshot.child(uid).child("State").getValue(String.class);
                cust_pincode=snapshot.child(uid).child("Pincode").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phar_name=snapshot.child(sid).child("First Name").getValue(String.class);
                phar_last=snapshot.child(sid).child("Last Name").getValue(String.class);
                phar_Address=snapshot.child(sid).child("House").getValue(String.class);
                phar_area=snapshot.child(sid).child("Area").getValue(String.class);
                phar_city=snapshot.child(sid).child("City").getValue(String.class);
                phar_state=snapshot.child(sid).child("State").getValue(String.class);
                phar_pincode=snapshot.child(sid).child("Pincode").getValue(String.class);


                dm.setPhar_name(phar_name+phar_last);
                dm.setPhar_Address(phar_Address+phar_area+phar_city+phar_pincode+phar_state);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        add_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("onClick: ","add cart in");

                dm.setCust_name(cust_name+cust_lastname);
                dm.setCust_Address(cust_Address+cust_area+cust_city+cust_pincode+cust_state);
dm.setImageURL(imageURL);
                dm.setPhar_name(phar_name+phar_last);
                dm.setPhar_Address(phar_Address+phar_area+phar_city+phar_pincode+phar_state);
                dm.setProduct_name(name);
                dm.setProduct_price(product_price);
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference3 = firebaseDatabase.getReference("cart");

                databaseReference3.child(cust_name+cust_lastname).child(name).setValue(dm);

                Toast.makeText(getApplicationContext(),"add successful",Toast.LENGTH_LONG).show();

            }
        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Confirm_order.this,Allinone.class);
                startActivity(intent);
                finish();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dm.setCust_name(cust_name+cust_lastname);
                dm.setCust_Address(cust_Address+cust_area+cust_city+cust_pincode+cust_state);

                dm.setPhar_name(phar_name+phar_last);
                dm.setPhar_Address(phar_Address+phar_area+phar_city+phar_pincode+phar_state);
                dm.setProduct_name(name);
                dm.setProduct_price(product_price);
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference3 = firebaseDatabase.getReference("Order");

                databaseReference3.child(name+cust_name+cust_lastname).setValue(dm);

                Toast.makeText(getApplicationContext(),"ordered successful",Toast.LENGTH_LONG).show();
            }
        });







    }
}
