package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseOne extends AppCompatActivity {
    Button Pharmacist,Customer,DeliveryPerson;
    Intent intent;
    String type;
    ConstraintLayout bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.background),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic1),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic3),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic4),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic5),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.back),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic6),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic10),3000);


        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        bgimage = findViewById(R.id.back3);
        bgimage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();
        Pharmacist = (Button)findViewById(R.id.Pharmacist);
        DeliveryPerson = (Button)findViewById(R.id.delivery);
        Customer = (Button)findViewById(R.id.customer);

        Pharmacist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type.equals("Email")) {
                    Intent loginemail = new Intent(ChooseOne.this, Pharmacistlogin.class);
                    startActivity(loginemail);
                    finish();
                }
                if (type.equals("Phone")) {
                    Intent loginphone = new Intent(ChooseOne.this, Pharmacistloginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if (type.equals("SignUp")) {
                    Intent Register = new Intent(ChooseOne.this, PharmacistRegistration.class);
                    startActivity(Register);

                }
            }
        });

        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemailcust  = new Intent(ChooseOne.this,Login.class);
                    startActivity(loginemailcust);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphonecust  = new Intent(ChooseOne.this,Loginphone.class);
                    startActivity(loginphonecust);
                    finish();
                }
                if(type.equals("SignUp")) {
                    Intent Registercust = new Intent(ChooseOne.this, Registration.class);
                    startActivity(Registercust);
                }
            }
        });

        DeliveryPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("Email")){
                    Intent loginemail = new Intent(ChooseOne.this,Delivery_Login.class);
                    startActivity(loginemail);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphone  = new Intent(ChooseOne.this,Delivery_Loginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Registerdelivery  = new Intent(ChooseOne.this,Delivery_Registration.class);
                    startActivity(Registerdelivery);
                }

            }
        });

    }
}