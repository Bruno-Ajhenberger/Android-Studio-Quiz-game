package com.example.myproject;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface QuestionDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void InsertQuestion(Question...question);

    @Query("SELECT *FROM Questions_Table")
    List<Question> getQuestions();

    @Query("DELETE  FROM Questions_table")
    void deleteQuestions();
}
