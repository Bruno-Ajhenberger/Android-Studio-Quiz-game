package com.example.myproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class GameScreenFragment extends Fragment  {

    private TextView tv,tvTimer;
    private RelativeLayout relativeLayout;
    private OnClickAnswerInterface onClickAnswerInterface;
    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private ImageView ivTime;
    private Random rng;
    private boolean running=true;
    private Question question;
    private int correntIndex;
    private int streak;

    @Override
    public void onPause() {
        super.onPause();
        running=false;
        onClickAnswerInterface.pause();

    }

    public GameScreenFragment(OnClickAnswerInterface onClickAnswerInterface, Question question, int streak) {
        this.onClickAnswerInterface = onClickAnswerInterface;
        this.question = question;
        this.streak=streak+1;

    }


    public static GameScreenFragment newInstance(OnClickAnswerInterface onClickAnswerInterface, Question question, int streak) {
        GameScreenFragment fragment = new GameScreenFragment(onClickAnswerInterface, question, streak);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUI(view);
        setUpListeners();
        onClickAnswerInterface.tick();

    }

    private void setUpListeners() {
        btnAnswer1.setOnClickListener(v -> clicked(1,correntIndex));
        btnAnswer2.setOnClickListener(v -> clicked(2,correntIndex));
        btnAnswer3.setOnClickListener(v -> clicked(3,correntIndex));
        btnAnswer4.setOnClickListener(v -> clicked(4,correntIndex));
    }

    private void initializeUI(View view) {
        rng= new Random();
        tv = view.findViewById(R.id.tvv);
        tvTimer = view.findViewById(R.id.tvTimer);
        relativeLayout = view.findViewById(R.id.relLayoutGameScreenFragment);
        btnAnswer1 = view.findViewById(R.id.btnAnswer1);
        btnAnswer2 = view.findViewById(R.id.btnAnswer2);
        btnAnswer3 = view.findViewById(R.id.btnAnswer3);
        btnAnswer4 = view.findViewById(R.id.btnAnswer4);
        ivTime = view.findViewById(R.id.imvTimeBar);
        //relativeLayout.setBackgroundResource(R.drawable.pic2);
        tv.setText(question.getQuestion().replaceAll("&quot;","'").replaceAll("&#039;","'"));
        setUpButtons();



    }

    private void setUpButtons() {
        int i = rng.nextInt(100);
            if(i < 26){
                btnAnswer1.setText(question.getIncAnswer1());
                btnAnswer3.setText(question.getIncAnswer2());
                btnAnswer2.setText(question.getIncAnswer3());
                btnAnswer4.setText(question.getCorrectAnswer());
                correntIndex=4;
            }
            else if(i > 25 && i< 51){
                btnAnswer1.setText(question.getIncAnswer1());
                btnAnswer3.setText(question.getCorrectAnswer());
                btnAnswer2.setText(question.getIncAnswer3());
                btnAnswer4.setText(question.getIncAnswer2());
                correntIndex=3;
            }else if(i> 50 && i< 75){
                btnAnswer1.setText(question.getIncAnswer1());
                btnAnswer3.setText(question.getIncAnswer2());
                btnAnswer2.setText(question.getCorrectAnswer());
                btnAnswer4.setText(question.getIncAnswer3());
                correntIndex=2;
            }else {
                btnAnswer1.setText(question.getCorrectAnswer());
                btnAnswer3.setText(question.getIncAnswer2());
                btnAnswer2.setText(question.getIncAnswer3());
                btnAnswer4.setText(question.getIncAnswer1());
                correntIndex=1;
            }


    }

    public void time() {
        if(running){
            onClickAnswerInterface.reDrawFragment();
        }

    }

    public void change(int time){
        tvTimer.setText(""+time +"s");
        ivTime.setScaleX((float) (time*3.33/100));

    }

    private void clicked(int pressed, int correct){
        btnAnswer1.setEnabled(false);
        btnAnswer2.setEnabled(false);
        btnAnswer3.setEnabled(false);
        btnAnswer4.setEnabled(false);
        if(correct == 1){
            btnAnswer1.setBackgroundResource(R.drawable.btn_correct);
        }
        if(correct == 2){
            btnAnswer2.setBackgroundResource(R.drawable.btn_correct);
        }
        if(correct == 3){
            btnAnswer3.setBackgroundResource(R.drawable.btn_correct);
        }
        if(correct == 4){
            btnAnswer4.setBackgroundResource(R.drawable.btn_correct);
        }
        if(pressed == 1 && correct != pressed){
            btnAnswer1.setBackgroundResource(R.drawable.btn_incorrect);
        }
        if(pressed == 2 && correct != pressed){
            btnAnswer2.setBackgroundResource(R.drawable.btn_incorrect);
        }
        if(pressed == 3 && correct != pressed){
            btnAnswer3.setBackgroundResource(R.drawable.btn_incorrect);
        }
        if(pressed == 4 && correct != pressed){
            btnAnswer4.setBackgroundResource(R.drawable.btn_incorrect);
        }
        if(correct == pressed){
            tv.setText("Correct!!  Current streak: "  +streak);
            onClickAnswerInterface.answer2Clicked();
        }
        else if(pressed != correct){
            tv.setText("InCorrect :( ");
            onClickAnswerInterface.answer3Clicked();
        }
        onClickAnswerInterface.answer1Clicked();




    }
}