package com.example.pharmeasy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Pharmacist_Fragment extends Fragment {
    FloatingActionButton f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_pharmacist_, container,false);
        f=(FloatingActionButton) view.findViewById(R.id.floatbutton);


        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Product_RegistrationForm.class);
                startActivity(intent);


            }

        });
        return view;
        }
    }

