package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class Allinone extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    private ViewPager viewPager;
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allinone);
        Bundle extras = getIntent().getExtras();
        flag=extras.getString("val1");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        viewPager=findViewById(R.id.main_viewpager);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        addTabs(viewPager);

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
        // override the onOptionsItemSelected()
        // function to implement
        // the item click listener callback
        // to open and close the navigation
        // drawer when the icon is clicked
        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){

            if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter =new ViewPagerAdapter(getSupportFragmentManager());

        if(flag.equals("Pharmacist")){
            adapter.addFrag(new Pharmacist_Fragment());
        }
        if(flag.equals("Customer")){

            adapter.addFrag(new Customer_Fragment() );
        }
        if(flag.equals("DeliveryPerson")){
            adapter.addFrag(new DeliveryPerson_Fragment() );
        }
        viewPager.setAdapter(adapter);
    }
}

