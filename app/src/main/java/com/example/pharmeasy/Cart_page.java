package com.example.pharmeasy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Cart_page extends AppCompatActivity {
    RecyclerView cart_re;
     String cn;
    int total_price;
    Button buy_btn;
    FirebaseDatabase firebaseDatabase;
    Cart_Recycler_adapter adapter;
    DatabaseReference databaseReference,databaseReference2,databaseReference3;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        cart_re=(RecyclerView) findViewById(R.id.cart_recyclerView);
        buy_btn=(Button)findViewById(R.id.buybutton_cart) ;
        total=(TextView)findViewById(R.id.Cart_Total);
cn=getIntent().getExtras().get("n1").toString();
        Log.d( "onCreate: ",cn);
        databaseReference=FirebaseDatabase.getInstance().getReference("cart").child(cn);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot datasnapshot:snapshot.getChildren())
                {
                    Cart_model c=datasnapshot.getValue(Cart_model.class);
                    total_price+= Integer.parseInt(c.getProduct_price());
                    Log.d("onDataChange cart: ",String.valueOf(total_price));
                    //  l1.add(p);
                    total.setText(String.valueOf(total_price));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        AlertDialog.Builder builder=new AlertDialog.Builder(this);


        cart_re.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FirebaseRecyclerOptions<Cart_model> options =
                new FirebaseRecyclerOptions.Builder<Cart_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cart").child(cn), Cart_model.class)
                        .build();
        adapter=new Cart_Recycler_adapter(options);
cart_re.setAdapter(adapter);

        total.setText(String.valueOf(total_price));
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("cash on delivery "+total_price+" /rs") ;

                //Setting message manually and performing action on button click

                builder  .setCancelable(false)
                        .setPositiveButton("order",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                buy_all();
                            }
                        });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Comfirm order");
                alert.show();

            }

        });


    }

    @Override
    public void onStart() {
        super.onStart();
       adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
     adapter.stopListening();
        
    }
    public  void buy_all(){


        databaseReference2=FirebaseDatabase.getInstance().getReference("cart").child(cn);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot:snapshot.getChildren())
                {
                    DataModel dm=new DataModel();
                    Cart_model c=datasnapshot.getValue(Cart_model.class);
                    dm.setCust_Address(c.getCust_Address());
                    dm.setCust_name(c.getCust_name());
                    dm.setPhar_Address(c.getPhar_Address());
                    dm.setPhar_name(c.getPhar_name());
                    dm.setProduct_name(c.getProduct_name());
                    dm.setProduct_price(c.getProduct_price());

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference3 = firebaseDatabase.getReference("Order");

                    databaseReference3.child(c.getProduct_name()+cn).setValue(dm);
                    FirebaseDatabase.getInstance().getReference("cart").child(cn).child(c.getProduct_name()).removeValue();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}
