package com.example.respect.cashbackapp.Items;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

/**
 * Created by respect on 6/2/2018.
 */

public class History {

    private String venueName;
    private Drawable venueLogo;
    private String time;
    private String date;
    private String cashbackPresent;
    private int totalAmount;
    private int transactionId;


    public History(String venueName, Drawable venueLogo, String time, String date, String cashbackPresent, int totalAmount,int transactionId) {
        this.venueName = venueName;
        this.venueLogo = venueLogo;
        this.time = time;
        this.date = date;
        this.cashbackPresent = cashbackPresent;
        this.totalAmount = totalAmount;
        this.transactionId=transactionId;
    }

    public String getVenueName() {
        return venueName;
    }

    public Drawable getVenueLogo() {
        return venueLogo;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getCashbackPresent() {
        return cashbackPresent;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTransactionId() {
        return transactionId;
    }
}
