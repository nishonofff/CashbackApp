package com.example.respect.cashbackapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.respect.cashbackapp.Constants;
import com.example.respect.cashbackapp.Activities.VenueActivity;
import com.example.respect.cashbackapp.R;
import com.example.respect.cashbackapp.Items.Venue;

import java.util.ArrayList;

/**
 * Created by respect on 6/2/2018.
 */

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.ViewHolder> implements View.OnClickListener {


    private Venue venueList;
    private Context context;
    private String imageTransition=Constants.KEY_IMAGE_TRANSITION;
    private String nameTransition=Constants.KEY_NAME_TRANSITION;
    public VenueAdapter(Context context) {
        this.context=context;
        venueList=new Venue(this.context);
    }



    public Venue getVenueList() {
        return venueList;
    }

    @Override
    public VenueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.venue_item, parent, false);

        return new VenueAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VenueAdapter.ViewHolder holder, final int position) {
        holder.venueName.setText(venueList.getName(position));
        holder.venueAddress.setText(venueList.getAddress(position));
        holder.venueImage.setImageDrawable(venueList.getLogo(position));
        holder.venueCashback.setText(venueList.getCashback(position));
        holder.venueItem.setOnClickListener(this);
        holder.detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){


                    case R.id.details_button:{
                        v.setVisibility(View.GONE);
                        Intent intent=new Intent(context,VenueActivity.class);
                        intent.putExtra(Constants.KEY_POSITION,position);
                        ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(
                                (Activity) context,
                                new Pair[]{Pair.create(holder.venueImage, imageTransition),
                                Pair.create(holder.venueName,nameTransition)
                                }
                                );
                        context.startActivity(intent,options.toBundle());
                    }

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return venueList.getSize();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.venue_item:{
                Button button=v.findViewById(R.id.details_button);
                button.setVisibility((button.getVisibility()==View.VISIBLE) ? View.GONE:View.VISIBLE);
                break;
            }


        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView venueName;
        TextView venueAddress;
        TextView venueCashback;
        CardView venueItem;
        Button detailsButton;
        ImageView venueImage;

        public ViewHolder(View itemView) {
            super(itemView);
            venueName = itemView.findViewById(R.id.venue_name);
            venueAddress = itemView.findViewById(R.id.venue_address);
            venueCashback = itemView.findViewById(R.id.cashback_present);
            venueImage=itemView.findViewById(R.id.venue_image);
            venueItem = itemView.findViewById(R.id.venue_item);
            detailsButton=itemView.findViewById(R.id.details_button);
        }
    }



    public void setFilter(Venue arrayList){

        venueList=new Venue(context);
        venueList=arrayList;
        notifyDataSetChanged();
    }


}
