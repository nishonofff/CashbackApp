package com.example.respect.cashbackapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.respect.cashbackapp.Adapters.HistoryAdapter;
import com.example.respect.cashbackapp.Constants;
import com.example.respect.cashbackapp.Items.History;
import com.example.respect.cashbackapp.R;

import java.util.ArrayList;

public class HistoryDetailsActivity extends AppCompatActivity {

    private ImageView historyLogo;
    private TextView transactionId;
    private TextView transactionStatus;
    private TextView dateAndTime;
    private TextView totalAmount;
    private TextView cashbackAmount;
    private TextView cashbackPresent;
    private HistoryAdapter historyAdapter;
    private ArrayList<History> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        historyLogo=findViewById(R.id.history_image_logo);
        transactionId=findViewById(R.id.transaction_id);
        transactionStatus=findViewById(R.id.transaction_status);
        totalAmount=findViewById(R.id.history_total_amount);
        cashbackAmount=findViewById(R.id.history_cashback_amount);
        cashbackPresent=findViewById(R.id.history_cashback_present);
        dateAndTime=findViewById(R.id.date_and_time);
        int position=getIntent().getIntExtra(Constants.KEY_POSITION,0);
        historyAdapter=new HistoryAdapter(this);

        historyList=historyAdapter.getHistoryList();
        historyLogo.setImageDrawable(historyList.get(position).getVenueLogo());
        totalAmount.setText(String.format("%d sum",historyList.get(position).getTotalAmount()));
        cashbackPresent.setText(historyList.get(position).getCashbackPresent());
        cashbackAmount.setText(getCashback(position));
        dateAndTime.setText(getDateAndTime(position));
        transactionId.setText(String.format("%d",historyList.get(position).getTransactionId()));

    }

    private String getCashback(int position) {
        String[] strings=historyList.get(position).getCashbackPresent().split(" ");
        int cashbackPresent= Integer.parseInt(strings[0])*historyList.get(position).getTotalAmount()/100;
        return String.format("%d sum",cashbackPresent);
    }

    private String getDateAndTime(int position) {

        return historyList.get(position).getDate()+" - "+historyList.get(position).getTime();

    }
}
