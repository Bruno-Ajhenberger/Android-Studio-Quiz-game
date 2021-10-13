package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameScreen extends AppCompatActivity implements OnClickAnswerInterface{

    private GameScreenFragment gameScreenFragment;
    private FragmentManager fragmentManager;
    private ArrayList<Integer> backgroundsList;
    private CountDownTimer countDownTimer;
    private long currentTick=30000;
    private List<Question> questionList;
    private Question question;
    private Random rng;
    private List<Boolean> checked;
    private int streak=0;
    private int tmp;
    private ArrayList<Integer> statscorrect;
    private ArrayList<Integer> statsIncorrect;
    public static final String SHARED_PREFERENCES = "prefs";
    public static final String CORRECT = "correct";
    public static final String INCORRECT = "incorrect";
    public String string = "dfawd";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        getBackgrounds();
        fragmentManager = getSupportFragmentManager();
        rng = new Random();
        Intent i = getIntent();
        questionList = new ArrayList<Question>();
        questionList = (List<Question>) i.getSerializableExtra("Questions");
        checked = new ArrayList<Boolean>();
        checked = (List<Boolean>) i.getSerializableExtra("checkedCategories");
        statscorrect = new ArrayList<Integer>();
        statsIncorrect = new ArrayList<Integer>();
        loadStats();

        if(statscorrect == null || statscorrect.isEmpty()){
            statscorrect = new ArrayList<Integer>();
            for (int ii=0;ii<12;ii++){
                statscorrect.add(0);
            }
        }
        if(statsIncorrect == null || statsIncorrect.isEmpty()){
            statsIncorrect = new ArrayList<Integer>();
            for (int ii=0;ii<12;ii++){
                statsIncorrect.add(0);

            }
        }


        //Toast.makeText(GameScreen.this, questionList.get(113).getCorrectAnswer() + "  " +questionList.size() , Toast.LENGTH_SHORT).show();
        setUpFragments();
    }

    private void loadStats() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CORRECT,null);
        String json2 = sharedPreferences.getString(INCORRECT,null);
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        statscorrect = gson.fromJson(json,type);
        statsIncorrect = gson.fromJson(json2,type);
    }


    private void getBackgrounds() {
        backgroundsList = new ArrayList<Integer>();
        backgroundsList.add(R.drawable.pic1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(statscorrect);
        editor.putString(CORRECT,json);
        String json2 = gson.toJson(statsIncorrect);
        editor.putString(INCORRECT,json2);
        editor.commit();
        getIntent().putExtra("dwad",string );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void setUpFragments() {
        //question = questionList.get(rng.nextInt(120));
        question = questionList.get(113);
        GenerateQuestion();
        gameScreenFragment = new GameScreenFragment(this,question,streak);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.gamefragment,gameScreenFragment,"awfwf");
        fragmentTransaction.commit();

    }

    private void GenerateQuestion() {
        int index = rng.nextInt(120);
        question=questionList.get(index);
        tmp=(int )Math.floor(index/10 );
        if(checked.get(tmp) == false){
            GenerateQuestion();
        }
    }

    @Override
    public void reDrawFragment() {
            setUpFragments();
    }

    @Override
    public void tick() {
        timerStart();
    }

    @Override
    public void pause() {
        pauseTimer();
    }

    @Override
    public void answer1Clicked() {
        countDownTimer.cancel();
        resetTimer(2);
        timerStart();
    }

    @Override
    public void answer2Clicked() {
            streak=streak+1;
            int temp;
            temp=statscorrect.get(tmp);
            temp = temp +1;
            statscorrect.set(tmp,temp);
    }

    @Override
    public void answer3Clicked() {
        streak=0;
        int temp;
        temp=statsIncorrect.get(tmp);
        temp = temp +1;
        statsIncorrect.set(tmp,temp);
    }

    @Override
    public void answer4Clicked() {

    }
    private void timerStart(){

            countDownTimer = new CountDownTimer(currentTick , 1000) {
            GameScreenFragment fragment = (GameScreenFragment)fragmentManager.findFragmentByTag("awfwf");
            @Override
            public void onTick(long millisUntilFinished) {
                currentTick = millisUntilFinished;
                fragment.change((int) (currentTick / 1000) + 1 );
                Log.d("timer",""+ currentTick);Log.d("timer",""+ currentTick);
            }

            @Override
            public void onFinish() {
                        currentTick = 30000;
                        reDrawFragment();
            }
        }.start();
    }
    private void pauseTimer(){
            countDownTimer.cancel();
    }
    private void resetTimer(int time){
        currentTick = time*1000;
    }

}
