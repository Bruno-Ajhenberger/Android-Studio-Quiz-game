package com.example.myproject;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ItemView> implements RecyclerViewOnClicl{

    private Bundle bundleData;



    public RecyclerViewAdapter(Bundle bundleData) {
        this.bundleData = bundleData;

    }

    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_item,parent,false);
        return new ItemView(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {
        double percentage;
        if(bundleData.getIntegerArrayList("incorrect").get(position) != 0){
            double tmp1 = (double) bundleData.getIntegerArrayList("correct").get(position);
            double tmp2 = (double) bundleData.getIntegerArrayList("incorrect").get(position);
            double tmp3 = tmp1 + tmp2;
            Log.d("gluposti",bundleData.getIntegerArrayList("correct").get(position).toString() +" "+ bundleData.getIntegerArrayList("incorrect").get(position));
            percentage = (double) tmp1 / tmp3 *100;
            Log.d("glupo" ," "+percentage);
        }else{
            percentage=100;
        }
        holder.setName(bundleData.getStringArrayList("categories").get(position));
        holder.setTopBarColor(position < 6 ? bundleData.getIntegerArrayList("barstyles").get(position) : bundleData.getIntegerArrayList("barstyles").get(position-6));
        holder.setPlayerStat("" +String.format("%.2f",percentage));
        holder.setGlobalStats(bundleData.getIntegerArrayList("scores").get(position+11).toString());
        holder.setTopBarSize((int)percentage);
        holder.setBottmBarSize(bundleData.getIntegerArrayList("scores").get(position + 11));
    }


    @Override
    public int getItemCount() {
        return   12;
    }

    @Override
    public void selectedChanged(Boolean deselected) {

    }
}
