package com.example.respect.cashbackapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.respect.cashbackapp.Adapters.ImageAdapter;
import com.example.respect.cashbackapp.Adapters.VenueAdapter;
import com.example.respect.cashbackapp.Constants;
import com.example.respect.cashbackapp.R;
import com.example.respect.cashbackapp.Items.Venue;

public class VenueActivity extends AppCompatActivity implements View.OnClickListener {

    private VenueAdapter venueAdapter;
    private Venue venueList;
    private Toolbar toolbar;
    ImageView toolbarImage;
    ImageView venueLogo;
    private TextView venueName;
    private TextView venueAddress;
    private TextView venuePhone;
    private TextView venueMail;
    private TextView venueWebsite;
    private int position;
    private RecyclerView imageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        venueAdapter = new VenueAdapter(this);
        venueList = venueAdapter.getVenueList();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbarImage = findViewById(R.id.toolbar_image_view);
        position = getIntent().getIntExtra(Constants.KEY_POSITION, 0);
        venueLogo=findViewById(R.id.venue_logo);
        venueName = findViewById(R.id.venue_name);
        venueAddress = findViewById(R.id.venue_address);
        venueName.setText(venueList.getName(position));
        venueAddress.setText(venueList.getAddress(position));
        toolbarImage.setImageDrawable(venueList.getCover(position));
        venuePhone = findViewById(R.id.venue_phone);
        venueMail = findViewById(R.id.venue_mail);
        venueWebsite = findViewById(R.id.venue_website);
        venueLogo.setImageDrawable(venueList.getLogo(position));
        venuePhone.setText(venueList.getPhone(position));
        venueMail.setText(venueList.getMail(position));


        imageContainer=findViewById(R.id.image_container);
        imageContainer.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        imageContainer.setAdapter(new ImageAdapter(this));
        venuePhone.setOnClickListener(this);
        venueMail.setOnClickListener(this);
        venueWebsite.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
            }

        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.venue_phone: {


                String number = venueList.getPhone(position);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);

                break;
            }

            case R.id.venue_mail: {
                String mail = venueList.getMail(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto: " + mail));
                startActivity(Intent.createChooser(intent,"Select"));
                break;
            }

            case R.id.venue_website: {
                String url = venueList.getWebsite(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+url));
                startActivity(Intent.createChooser(intent,"Select"));
                break;
            }


        }
    }

}
