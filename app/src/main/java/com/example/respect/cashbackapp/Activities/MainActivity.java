package com.example.respect.cashbackapp.Activities;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.respect.cashbackapp.Adapters.VenueAdapter;
import com.example.respect.cashbackapp.Items.History;
import com.example.respect.cashbackapp.Items.Venue;
import com.example.respect.cashbackapp.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView venueContiner;
    private VenueAdapter venueAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        venueContiner = findViewById(R.id.venue_container);
        venueContiner.setLayoutManager(new LinearLayoutManager(this));
        venueAdapter=new VenueAdapter(this);
        venueContiner.setAdapter(venueAdapter);
        navigationView.setNavigationItemSelectedListener(this);



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home: {
                drawerLayout.openDrawer(GravityCompat.START);
                break;}
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.navigation_view_history:{
                Intent intent=new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.navigation_view_exit:{
                finish();
            }
        }

        return true;
    }



}
