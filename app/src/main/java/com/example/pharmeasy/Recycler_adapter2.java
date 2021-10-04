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


public class Recycler_adapter2 extends FirebaseRecyclerAdapter<Model, Recycler_adapter2.Myholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Recycler_adapter2(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Myholder myholder, int i, @NonNull Model model) {
        myholder.name_text.setText(model.getName());
        myholder.price_text.setText(model.getProduct_price());
        Glide.with(myholder.img_view.getContext()).load(model.getImageURL()).into(myholder.img_view);
        myholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                Intent inti=new Intent(activity,See_product.class);
                inti.putExtra("name",model.getName());
                inti.putExtra("manufacture",model.getAbout_manufacture());
                inti.putExtra("expiry_date",model.getExpiry_date());
                inti.putExtra("imageURL",model.getImageURL());
                inti.putExtra("product_description",model.getProduct_description());
                inti.putExtra("product_price",model.getProduct_price());
                inti.putExtra("sid",model.getSid());
                activity.startActivity(inti);
            }
        });
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card,parent,false);
        return new Myholder(view);
    }

    class Myholder extends RecyclerView.ViewHolder {
        ImageView img_view;
        TextView name_text,price_text;
        CardView cardView;
        public Myholder(@NonNull View itemView) {

            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cust_card);
            img_view=(ImageView) itemView.findViewById(R.id.cust_image);
            name_text=(TextView) itemView.findViewById(R.id.pro_name);
            price_text=(TextView) itemView.findViewById(R.id.pro_price);
        }
    }
}
