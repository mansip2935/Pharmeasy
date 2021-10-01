package com.example.pharmeasy;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Customer_Fragment extends Fragment {
    RecyclerView CovidEssentials_re,Homecare_re,Babycare_re,Devices_re,WomenCare_re,NutritionandHealthcareSuppliments_re;
    private static Recycler_adapter CovidEssentialsadapter,Homecareadapter,Babycareadapter,Devicesadapter,WomenCareadapter,NutritionandHealthcareSupplimentsadapter;


    private static ArrayList<DataModel> data;

    LinearLayoutManager HorizontalLayout, HorizontalLayout2,HorizontalLayout3,HorizontalLayout4,HorizontalLayout5,HorizontalLayout6;
    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_customer_, container, false);
        //myOnClickListener = new MyOnClickListener(this);

        CovidEssentials_re = (RecyclerView) view.findViewById(R.id.Recycler_covidessential);
        Homecare_re= (RecyclerView) view.findViewById(R.id.Recycler_homecare);
        Babycare_re= (RecyclerView) view.findViewById(R.id.Recycler_babycare);
        Devices_re= (RecyclerView) view.findViewById(R.id.Recycler_devices);
        WomenCare_re= (RecyclerView) view.findViewById(R.id.Recycler_womencare);
        NutritionandHealthcareSuppliments_re= (RecyclerView) view.findViewById(R.id.Recycler_suppliments);

        HorizontalLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        HorizontalLayout2= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        HorizontalLayout3= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        HorizontalLayout4= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        HorizontalLayout5= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        HorizontalLayout6= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        CovidEssentials_re.setLayoutManager(HorizontalLayout);
        Homecare_re.setLayoutManager(HorizontalLayout2);
        Babycare_re.setLayoutManager(HorizontalLayout3);
        Devices_re.setLayoutManager(HorizontalLayout4);
        WomenCare_re.setLayoutManager(HorizontalLayout5);
        NutritionandHealthcareSuppliments_re.setLayoutManager(HorizontalLayout6);

        FirebaseRecyclerOptions<Model> optionsCovidEssentials =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Covid Essentials"), Model.class)
                        .build();
        CovidEssentialsadapter=new Recycler_adapter(optionsCovidEssentials);
        CovidEssentials_re.setAdapter(CovidEssentialsadapter);

        FirebaseRecyclerOptions<Model> optionsBabycare =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Baby care"), Model.class)
                        .build();
        Babycareadapter=new Recycler_adapter(optionsBabycare);
        Babycare_re.setAdapter(Babycareadapter);
        FirebaseRecyclerOptions<Model> optionsHomecare =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Home care"), Model.class)
                        .build();
        Homecareadapter=new Recycler_adapter(optionsHomecare);
        Homecare_re.setAdapter(Homecareadapter);

        FirebaseRecyclerOptions<Model> optionsDevices =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Devices"), Model.class)
                        .build();
        Devicesadapter=new Recycler_adapter(optionsDevices);
        Devices_re.setAdapter(Devicesadapter);
        FirebaseRecyclerOptions<Model> optionsWomenCare =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Women Care"), Model.class)
                        .build();
        WomenCareadapter=new Recycler_adapter(optionsWomenCare);
        WomenCare_re.setAdapter(WomenCareadapter);
        FirebaseRecyclerOptions<Model> optionsNutritionandHealthcareSuppliments =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Nutrition and Healthcare Suppliments"), Model.class)
                        .build();
        NutritionandHealthcareSupplimentsadapter=new Recycler_adapter(optionsNutritionandHealthcareSuppliments);
        NutritionandHealthcareSuppliments_re.setAdapter(NutritionandHealthcareSupplimentsadapter);
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        CovidEssentialsadapter.startListening();
        Babycareadapter.startListening();
        Homecareadapter.startListening();
        Devicesadapter.startListening();
        WomenCareadapter.startListening();
        NutritionandHealthcareSupplimentsadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        CovidEssentialsadapter.stopListening();
        Babycareadapter.stopListening();
        Homecareadapter.startListening();
        Devicesadapter.startListening();
        WomenCareadapter.startListening();
        NutritionandHealthcareSupplimentsadapter.startListening();
    }
}