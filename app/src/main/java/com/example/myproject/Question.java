package com.example.myproject;



import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Questions_Table")
public class Question implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    private String category;
    private String question;
    private String incAnswer1;
    private String incAnswer2;
    private String incAnswer3;
    private String correctAnswer;


    public Question() {

    }


//region getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncAnswer1() {
        return incAnswer1;
    }

    public void setIncAnswer1(String incAnswer1) {
        this.incAnswer1 = incAnswer1;
    }

    public String getIncAnswer2() {
        return incAnswer2;
    }

    public void setIncAnswer2(String incAnswer2) {
        this.incAnswer2 = incAnswer2;
    }

    public String getIncAnswer3() {
        return incAnswer3;
    }

    public void setIncAnswer3(String incAnswer3) {
        this.incAnswer3 = incAnswer3;
    }


    //endregion
}
