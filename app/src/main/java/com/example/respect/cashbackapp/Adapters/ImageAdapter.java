package com.example.respect.cashbackapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.respect.cashbackapp.Activities.ShowImageActivity;
import com.example.respect.cashbackapp.Activities.VenueActivity;
import com.example.respect.cashbackapp.Constants;
import com.example.respect.cashbackapp.R;

/**
 * Created by respect on 6/4/2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private static TypedArray images;
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
        images=context.getResources().obtainTypedArray(R.array.photos);
    }

    public static TypedArray getImages() {
        return images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.imageView.setImageDrawable(images.getDrawable(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowImageActivity.class);
                intent.putExtra(Constants.KEY_POSITION,position);
                ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.imageView, ViewCompat.getTransitionName(holder.imageView));
                context.startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_image_view);
        }
    }
}
