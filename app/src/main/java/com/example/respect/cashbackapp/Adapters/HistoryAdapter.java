package com.example.respect.cashbackapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.respect.cashbackapp.Activities.HistoryActivity;
import com.example.respect.cashbackapp.Activities.HistoryDetailsActivity;
import com.example.respect.cashbackapp.Constants;
import com.example.respect.cashbackapp.Items.History;
import com.example.respect.cashbackapp.Items.HistoryList;
import com.example.respect.cashbackapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


/**
 * Created by respect on 6/2/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> implements AdapterView.OnItemSelectedListener {


    private Context context;
    /*private int position;*/
    private ArrayList<History> historyList;
    public HistoryAdapter(Context context) {
        this.context=context;
        HistoryList historyLists=new HistoryList(this.context);
        historyList=historyLists.getHistoryList();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_item, parent, false);
        HistoryActivity.spinner.setOnItemSelectedListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String[] strings=historyList.get(position).getCashbackPresent().split(" ");
        int cashbackPresent= Integer.parseInt(strings[0]);
        int cashbackAmount=historyList.get(position).getTotalAmount()*cashbackPresent/100;
        holder.venueName.setText(historyList.get(position).getVenueName());
        holder.venueLogo.setImageDrawable(historyList.get(position).getVenueLogo());
        holder.cashbackAmount.setText(String.format("%d sum",cashbackAmount));
        holder.historyDate.setText(historyList.get(position).getDate());
        holder.historyTime.setText(historyList.get(position).getTime());
        holder.historyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, HistoryDetailsActivity.class);
                intent.putExtra(Constants.KEY_POSITION,position);
                context.startActivity(intent);
            }
        });
    }
    

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position==0){
            historyList.sort(new Comparator<History>() {
                @Override
                public int compare(History o1, History o2) {

                    SimpleDateFormat format = new SimpleDateFormat(
                            "dd.MM.yyyy");
                    int compareResult = 0;
                    try {
                        Date arg0Date = format.parse(o1.getDate());
                        Date arg1Date = format.parse(o2.getDate());
                        compareResult = arg0Date.compareTo(arg1Date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return compareResult;

                }
            });

            HistoryActivity.historyAdapter.notifyDataSetChanged();
        }

        if (position==1){

            historyList.sort(new Comparator<History>() {
                @Override
                public int compare(History o1, History o2) {
                    return getCashback(o1)-getCashback(o2);
                }

                private int getCashback(History history) {
                    String[] strings=history.getCashbackPresent().split(" ");
                    int cashbackPresent= Integer.parseInt(strings[0]);
                    int cashbackAmount=history.getTotalAmount()*cashbackPresent/100;
                    return cashbackAmount;
                }
            });


            HistoryActivity.historyAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView venueName;
        TextView cashbackAmount;
        TextView historyDate;
        TextView historyTime;
        ImageView venueLogo;
        CardView historyItem;
        public ViewHolder(View itemView) {
            super(itemView);
            venueName = itemView.findViewById(R.id.history_venue_name);
            historyDate = itemView.findViewById(R.id.history_date);
            historyTime = itemView.findViewById(R.id.history_timee);
            cashbackAmount = itemView.findViewById(R.id.cashback_amount);
            venueLogo=itemView.findViewById(R.id.history_venue_logo);
            historyItem = itemView.findViewById(R.id.history_item);

        }
    }

    public ArrayList<History> getHistoryList() {
        return historyList;
    }
}
