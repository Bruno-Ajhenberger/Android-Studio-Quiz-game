package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnClickInterface, Serializable {

    private ViewPager viewpager;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private ArrayList<String> categoryNames;
    private ArrayList<Integer> barStyles;
    private ArrayList<Integer> scores;
    private Bundle bundleData;
    private Intent intent;
    private Random rng;
    public static QuestionDatabase questionDatabase;
    private Question question;
    private HandlerThread databaseThread = new HandlerThread("databaseThread");
    private Handler databaseHandler;
    public static List<Question> questionList;
    public static List<QuestionPOJO> questionPOJOList;
    private int[] categoryIndexArray;
    private ArrayList<String> quotes;
    private List<Boolean> checked;
    private ArrayList<Integer> correctCount;
    private ArrayList<Integer> incorrectCount;
    public static final String SHARED_PREFERENCES = "prefs";
    public static final String CORRECT = "correct";
    public static final String INCORRECT = "incorrect";
    public String ipstring;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getXmlResources();
        initializeUI();
        setUpViewPager();
        databaseThread.start();
        databaseHandler = new Handler(databaseThread.getLooper());
        questionDatabase = Room.databaseBuilder(getApplicationContext(),QuestionDatabase.class,"Questions").build();
        loadDatabase();

    }


    private void setUpViewPager() {
        PagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,bundleData,this);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText("My Profile");
        tabLayout.getTabAt(1).setText("Home");
        tabLayout.getTabAt(2).setText("Stats");

    }

    private void initializeUI() {
            viewpager = findViewById(R.id.viewPager);
            tabLayout = findViewById(R.id.tabLayout);

        ipstring = intent.getStringExtra("mojString");
    }

    private void getXmlResources() {
        correctCount = new ArrayList<Integer>();
        incorrectCount = new ArrayList<Integer>();
        setUpSharedPreferences();
        if(correctCount == null || correctCount.isEmpty()){
            correctCount = new ArrayList<Integer>();
            for (int i=0;i<12;i++){
                correctCount.add(0);
            }
        }
        if(incorrectCount == null || incorrectCount.isEmpty()){
            incorrectCount = new ArrayList<Integer>();
            for (int i=0;i<12;i++){
                incorrectCount.add(0);

            }
        }
        categoryNames = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Categories)));
        categoryIndexArray = getResources().getIntArray(R.array.category_index);
        quotes = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Quotes)));
        barStyles = new ArrayList<Integer>();
        scores = new ArrayList<Integer>();
        barStyles.add(R.drawable.bar_blue);
        barStyles.add(R.drawable.bar_orange);
        barStyles.add(R.drawable.bar_purple);
        barStyles.add(R.drawable.bar_green);
        barStyles.add(R.drawable.bar_pink);
        barStyles.add(R.drawable.bar_yellow);
        barStyles.add(R.drawable.bar_grey);
        rng = new Random();
        for(int i=0;i<24;i++){
            scores.add(rng.nextInt(100));
        }
        if(checked == null || checked.isEmpty()) {
            checked = new ArrayList<Boolean>(Arrays.asList(new Boolean[12]));
            for (int i = 0; i < 12; i++) {
                checked.set(i,true);
            }
        }
        bundleData = new Bundle();
        bundleData.putIntegerArrayList("barstyles",barStyles);
        bundleData.putIntegerArrayList("scores",scores);
        bundleData.putStringArrayList("categories", categoryNames);
        bundleData.putStringArrayList("quotes", quotes);
        bundleData.putIntegerArrayList("correct", correctCount);
        bundleData.putIntegerArrayList("incorrect", incorrectCount);

    }

    private void setUpSharedPreferences() {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(CORRECT,null);
            String json2 = sharedPreferences.getString(INCORRECT,null);
            Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
            correctCount = gson.fromJson(json,type);
            incorrectCount = gson.fromJson(json2,type);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpSharedPreferences();
        setUpViewPager();
    }

    @Override
    public void playButtonClicked() {
                loadDatabase();
                if(questionList.isEmpty()){
                    Toast.makeText(this,"Update before playing",Toast.LENGTH_SHORT).show();
                }
                else{
                    intent = new Intent(this, GameScreen.class);
                    intent.putExtra("Questions", (Serializable) questionList);
                    intent.putExtra("checkedCategories" ,(Serializable) checked);
                    startActivity(intent);
                }

        //Toast.makeText(MainActivity.this, questionList.get(rng.nextInt(120)).getIncAnswer1() + "  " +questionList.size() , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void exitButtonClicked() {
        finish();
    }

    @Override
    public void updateButtonClicked() {

                databaseHandler.post(new DownloadQuestions(categoryIndexArray));

    }

    @Override
    public void saveButtonClicked(List<Boolean> checked) {
        this.checked = checked;
    }


    public void loadDatabase(){

                databaseHandler.post(new LoadDatabaseQuestions());
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseThread.quit();
    }


    public static class LoadDatabaseQuestions implements Runnable{


        @Override
        public void run() {
            questionList = new ArrayList<>();
            questionList = questionDatabase.questionDAO().getQuestions();
        }
    }
    public static class DownloadQuestions implements Runnable{

        int[] categoryIndexArray;
        private Question question;
        public DownloadQuestions(int[] categoryIndexArray){
            this.categoryIndexArray = categoryIndexArray;
            question = new Question();
        }

        private Call<Awdd> apicall;
        private boolean downloaded=false;

        @Override
        public void run() {
            questionPOJOList = new ArrayList<QuestionPOJO>();
            questionDatabase.questionDAO().deleteQuestions();

            for(int i = 0; i<categoryIndexArray.length; i++){
                setUpAPICall(categoryIndexArray[i]);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i = 0; i<questionPOJOList.size();i++) {
                question.setQuestion(questionPOJOList.get(i).getQuestion());
                question.setCategory(questionPOJOList.get(i).getCategory());
                question.setCorrectAnswer(questionPOJOList.get(i).getCorrect_answer());
                question.setIncAnswer1(questionPOJOList.get(i).getIncorrect_answers(0));
                question.setIncAnswer2(questionPOJOList.get(i).getIncorrect_answers(1));
                question.setIncAnswer3(questionPOJOList.get(i).getIncorrect_answers(2));

                questionDatabase.questionDAO().InsertQuestion(question);

            }


        }
        private void setUpAPICall(int index) {
            apicall = NetworkUtils.getApiInterface().getUsers(10, index, "multiple");
            apicall.enqueue(new Callback<Awdd>() {
                @Override
                public void onResponse(Call<Awdd> call, Response<Awdd> response) {
                    Log.d("apicall"," afwwaf" +response.body().getQuestions().get(0).getCategory() + "  " + categoryIndexArray.length);
                    questionPOJOList.addAll(response.body().getQuestions());
                }

                @Override
                public void onFailure(Call<Awdd> call, Throwable t) {
                    Log.d("apicall","" + t.getMessage());
                }
            });
        }

    }

}