package com.example.pharmeasy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Pharmacist_Fragment extends Fragment {
    FloatingActionButton f;
    RecyclerView CovidEssentials_re,Homecare_re,Babycare_re,Devices_re,WomenCare_re,NutritionandHealthcareSuppliments_re;
    private static Recycler_adapter2 CovidEssentialsadapter,Homecareadapter,Babycareadapter,Devicesadapter,WomenCareadapter,NutritionandHealthcareSupplimentsadapter;
    LinearLayoutManager HorizontalLayout, HorizontalLayout2,HorizontalLayout3,HorizontalLayout4,HorizontalLayout5,HorizontalLayout6;
    String uid;

    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_pharmacist_, container,false);
        f=(FloatingActionButton) view.findViewById(R.id.floatbutton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        CovidEssentials_re = (RecyclerView) view.findViewById(R.id.Recycler_covidessential_admin);
        Homecare_re= (RecyclerView) view.findViewById(R.id.Recycler_homecare_admin);
        Babycare_re= (RecyclerView) view.findViewById(R.id.Recycler_babycare_admin);
        Devices_re= (RecyclerView) view.findViewById(R.id.Recycler_devices_admin);
        WomenCare_re= (RecyclerView) view.findViewById(R.id.Recycler_womencare_admin);
        NutritionandHealthcareSuppliments_re= (RecyclerView) view.findViewById(R.id.Recycler_suppliments_admin);

        HorizontalLayout = new LinearLayoutManager(getContext());
        HorizontalLayout2= new LinearLayoutManager(getContext());
        HorizontalLayout3= new LinearLayoutManager(getContext());
        HorizontalLayout4= new LinearLayoutManager(getContext());
        HorizontalLayout5= new LinearLayoutManager(getContext());
        HorizontalLayout6= new LinearLayoutManager(getContext());

        CovidEssentials_re.setLayoutManager(HorizontalLayout);
        Homecare_re.setLayoutManager(HorizontalLayout2);
        Babycare_re.setLayoutManager(HorizontalLayout3);
        Devices_re.setLayoutManager(HorizontalLayout4);
        WomenCare_re.setLayoutManager(HorizontalLayout5);
        NutritionandHealthcareSuppliments_re.setLayoutManager(HorizontalLayout6);
        FirebaseRecyclerOptions<Model> optionsCovidEssentials =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Covid Essentials").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        CovidEssentialsadapter=new Recycler_adapter2(optionsCovidEssentials);
        CovidEssentials_re.setAdapter(CovidEssentialsadapter);

        FirebaseRecyclerOptions<Model> optionsBabycare =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Baby care").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        Babycareadapter=new Recycler_adapter2(optionsBabycare);
        Babycare_re.setAdapter(Babycareadapter);
        FirebaseRecyclerOptions<Model> optionsHomecare =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Home care").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        Homecareadapter=new Recycler_adapter2(optionsHomecare);
        Homecare_re.setAdapter(Homecareadapter);

        FirebaseRecyclerOptions<Model> optionsDevices =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Devices").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        Devicesadapter=new Recycler_adapter2(optionsDevices);
        Devices_re.setAdapter(Devicesadapter);
        FirebaseRecyclerOptions<Model> optionsWomenCare =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Women Care").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        WomenCareadapter=new Recycler_adapter2(optionsWomenCare);
        WomenCare_re.setAdapter(WomenCareadapter);
        FirebaseRecyclerOptions<Model> optionsNutritionandHealthcareSuppliments =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Nutrition and Healthcare Suppliments").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        NutritionandHealthcareSupplimentsadapter=new Recycler_adapter2(optionsNutritionandHealthcareSuppliments);
        NutritionandHealthcareSuppliments_re.setAdapter(NutritionandHealthcareSupplimentsadapter);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Product_RegistrationForm.class);
                startActivity(intent);


            }

        });
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

