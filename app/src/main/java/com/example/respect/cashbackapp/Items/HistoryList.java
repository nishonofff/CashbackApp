package com.example.respect.cashbackapp.Items;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.respect.cashbackapp.R;

import java.util.ArrayList;

/**
 * Created by respect on 6/4/2018.
 */

public class HistoryList {

    private String[] venueName;
    private TypedArray venueLogo;
    private String[] time;
    private String[] date;
    private String[] cashbackPresent;
    private int[] totalAmount;
    private int[] transactionId;
    private ArrayList<History> historyList;
    Context context;

    public HistoryList(Context context) {
        this.context = context;
        historyList=new ArrayList<>();
        venueName = context.getResources().getStringArray(R.array.venue_names);
        venueLogo = context.getResources().obtainTypedArray(R.array.venue_logos);
        time = context.getResources().getStringArray(R.array.history_time);
        date = context.getResources().getStringArray(R.array.history_date);
        cashbackPresent = context.getResources().getStringArray(R.array.venue_cashback_present);
        totalAmount = context.getResources().getIntArray(R.array.history_total_amount);
        transactionId = context.getResources().getIntArray(R.array.history_transaction_id);


        for (int i = 0; i < venueName.length; i++) {
            historyList.add(new History(venueName[i],venueLogo.getDrawable(i),time[i],date[i],cashbackPresent[i],totalAmount[i],transactionId[i]));
        }
    }

    public ArrayList<History> getHistoryList() {
        return historyList;
    }
}
