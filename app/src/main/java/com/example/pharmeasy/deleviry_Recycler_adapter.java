package com.example.pharmeasy;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class deleviry_Recycler_adapter extends FirebaseRecyclerAdapter<De_model,deleviry_Recycler_adapter.Myholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public deleviry_Recycler_adapter(@NonNull FirebaseRecyclerOptions<De_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Myholder myholder, int i, @NonNull De_model model) {
myholder.cust_Address_test.setText(model.cust_Address);
myholder.cust_name_test.setText(model.cust_name);
myholder.phar_name_test.setText(model.phar_name);
myholder.phar_Address_test.setText(model.phar_Address);
myholder.product_name_test.setText(model.product_name);
myholder.product_price_test.setText(model.product_price);

    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.order_to_delivery,parent,false);
        return new Myholder(view);
    }
    class Myholder extends RecyclerView.ViewHolder {

        TextView cust_Address_test, cust_name_test,imageURL_test,phar_Address_test,  phar_name_test,product_name_test,product_price_test;

        public Myholder(@NonNull View itemView) {

            super(itemView);
            cust_Address_test=(TextView) itemView.findViewById(R.id.order_to_customer_address);
            cust_name_test=(TextView) itemView.findViewById(R.id.order_to_customer_name);

            phar_Address_test=(TextView) itemView.findViewById(R.id.order_to_pharmacist_address);
            phar_name_test=(TextView) itemView.findViewById(R.id.order_to_pharmacist_name);
            product_name_test=(TextView) itemView.findViewById(R.id.order_to_product_name);
            product_price_test=(TextView) itemView.findViewById(R.id.order_to_product_price);


        }
    }
}