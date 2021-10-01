package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Allinone extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    String name,uid,last;
    DatabaseReference databaseeReference;
    FirebaseUser user;
    private ViewPager viewPager;
    String flag;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allinone);
        Bundle extras = getIntent().getExtras();
        flag=extras.getString("val1");

        navigationView = findViewById(R.id.nav_daw);

        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        databaseeReference= FirebaseDatabase.getInstance().getReference("Customer");
        databaseeReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name= snapshot.child(uid).child("First Name").getValue(String.class);
                last= snapshot.child(uid).child("Last Name").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
        navigationView.setNavigationItemSelectedListener(this);

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        //    int id=menu.getItemId();
        return true;
    }
    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked

    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        int id=item.getItemId();
        if(id==R.id.nav_cart){

            Intent inti =new Intent(Allinone.this,Cart_page.class);

            startActivity(inti);
            return true;
        }
        if(id==R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Allinone.this, MainMenu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.nav_cart){

            Intent inti =new Intent(Allinone.this,Cart_page.class);

            inti.putExtra("n1",name+last);
            startActivity(inti);

        }
        if(id==R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Allinone.this, MainMenu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.my_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

