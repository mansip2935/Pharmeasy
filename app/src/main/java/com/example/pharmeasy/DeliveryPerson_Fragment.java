package com.example.pharmeasy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class DeliveryPerson_Fragment extends Fragment {
    RecyclerView deleviry;
    private RecyclerView.LayoutManager layoutManager;
deleviry_Recycler_adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_person_, container, false);
        layoutManager = new LinearLayoutManager(getContext());
        deleviry=(RecyclerView)view.findViewById(R.id.delivery_recycler);
        deleviry.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<De_model> options =
                new FirebaseRecyclerOptions.Builder<De_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Order"), De_model.class)
                        .build();
        adapter=new deleviry_Recycler_adapter(options);
       deleviry.setAdapter(adapter);

        return view;
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