package com.example.myproject;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainPageFragment extends Fragment {

    private RelativeLayout relativeLayout;
    private AnimationDrawable animationDrawable;
    private OnClickInterface onClickInterface;
    private Button btnPlay, btnUpdate, btnExit;
    private TextView tvQuote;
    private ArrayList<String> quotes;
    private Random rng;


    public MainPageFragment(OnClickInterface onClickInterface, ArrayList<String> quotes){
        this.onClickInterface = onClickInterface;
        this.quotes = quotes;
    }

    public static MainPageFragment newInstance(OnClickInterface onClickInterface,ArrayList<String> quotes) {
        MainPageFragment fragment = new MainPageFragment(onClickInterface,quotes);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_page_fragent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUI(view);
        setUpListeners();
    }

    private void setUpListeners() {
            
            btnExit.setOnClickListener(v -> onClickInterface.exitButtonClicked());
            btnPlay.setOnClickListener(v -> onClickInterface.playButtonClicked());
            btnUpdate.setOnClickListener(v -> onClickInterface.updateButtonClicked());

    }

    private void initializeUI(View view) {
        rng = new Random();
        btnPlay = view.findViewById(R.id.btnPlay);
        btnUpdate = view.findViewById(R.id.btnWebSite);
        btnExit = view.findViewById(R.id.btnExit);
        tvQuote = view.findViewById(R.id.tvMainPageText);
        tvQuote.setText(quotes.get(rng.nextInt(8)));
        relativeLayout = view.findViewById(R.id.mainFragmentLayout);
        animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }
}
