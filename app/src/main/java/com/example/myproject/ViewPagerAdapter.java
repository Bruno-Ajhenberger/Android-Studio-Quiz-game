package com.example.myproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private Bundle bundleData;
    private OnClickInterface onClickInterface;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, Bundle bundleData, OnClickInterface onClickInterface) {
        super(fm, behavior);
        this.bundleData = bundleData;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
             return   OptionsFragment.newInstance(onClickInterface);
            case 1:
                return MainPageFragment.newInstance(onClickInterface, bundleData.getStringArrayList("quotes"));
            default:
                return StatsFragment.newInstance(bundleData);

        }
    }


    @Override
    public int getCount() {
        return 3;
    }
}
