package com.example.respect.cashbackapp.Items;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.example.respect.cashbackapp.R;

import java.util.ArrayList;

/**
 * Created by respect on 5/30/2018.
 */

public class Venue {

    private String[] name;
    private String[] address;
    private String[] cashbackPresent;
    private String[] phones;
    private String[] mails;
    private String[] websites;
    private TypedArray logo;
    private TypedArray cover;


    private Context context;
    public Venue(Context context) {
        this.context=context;
        name=context.getResources().getStringArray(R.array.venue_names);
        address=context.getResources().getStringArray(R.array.venue_address);
        cashbackPresent=context.getResources().getStringArray(R.array.venue_cashback_present);
        phones=context.getResources().getStringArray(R.array.venue_phones);
        mails=context.getResources().getStringArray(R.array.venue_mails);
        websites=context.getResources().getStringArray(R.array.venue_websites);
        logo=context.getResources().obtainTypedArray(R.array.venue_logos);
        cover=context.getResources().obtainTypedArray(R.array.venue_cover_photos);



    }


    public int getSize(){
        return name.length;
    }

    public String getName(int position) {
        return name[position];
    }

    public String getCashback(int position) {
        return cashbackPresent[position];
    }

    public String getAddress(int position) {
        return address[position];
    }

    public Drawable getLogo(int position) {
        return logo.getDrawable(position);
    }
    public int getLogoId(int position) {
        return logo.getResourceId(position,0);
    }


    public String getPhone(int position) {
        return phones[position];
    }

    public String getMail(int position) {
        return mails[position];
    }

    public String getWebsite(int position) {
        return websites[position];
    }

    public Drawable getCover(int position) {
        return cover.getDrawable(position);
    }
}
