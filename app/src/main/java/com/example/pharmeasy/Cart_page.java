package com.example.pharmeasy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.FirebaseDatabase;


public class Cart_page extends AppCompatActivity {
    RecyclerView cart_re;
     String cn;
    int total_price;
    Button buy_btn;

    Cart_Recycler_adapter adapter;

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
}
