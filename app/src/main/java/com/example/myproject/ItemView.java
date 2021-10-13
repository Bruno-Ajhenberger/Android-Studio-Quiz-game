package com.example.myproject;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;


public class ItemView extends RecyclerView.ViewHolder {

    private ImageView ivTopBar, ivBottmBar;
    private TextView tvCategory, tvTopPerc, tvBotPerc;
    private RecyclerViewOnClicl recyclerViewOnClicl;



    public ItemView(@NonNull View itemView, RecyclerViewOnClicl recyclerViewOnClicl) {
        super(itemView);
        this.recyclerViewOnClicl = recyclerViewOnClicl;
        innitializeUI();
    }

    private void innitializeUI() {
        ivTopBar = itemView.findViewById(R.id.imvTopBar);
        ivBottmBar = itemView.findViewById(R.id.imvBottmBar);
        tvCategory = itemView.findViewById(R.id.tvCategory);
        tvTopPerc = itemView.findViewById(R.id.tvPercTop);
        tvBotPerc = itemView.findViewById(R.id.tvPercBott);
        ivTopBar.setScaleType(ImageView.ScaleType.FIT_START);

    }

        public void setName(String name){
        tvCategory.setText(name);
    }

    public void setTopBarColor(int drawableID){
        ivTopBar.setImageResource(drawableID);
    }

    public void setPlayerStat(String stats){
        tvTopPerc.setText(stats + "%");
    }
    public void setGlobalStats(String stats){
        tvBotPerc.setText(stats + "%");
    }
    public void setTopBarSize(int sizeMod){
        ivTopBar.setPivotX(0);
        ivTopBar.setScaleX((float)sizeMod/100);
    }
    public void setBottmBarSize(int sizeMod){
        ivBottmBar.setPivotX(0);
        ivBottmBar.setScaleX((float)sizeMod/100);
    }

}
