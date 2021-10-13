package com.example.myproject;



public class QuestionPOJO {

    public String category;
    public String question;
    public String correct_answer;
    public String[] incorrect_answers;



    //region Getters Setters

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

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getIncorrect_answers(int i) {
        return incorrect_answers[i] ;
    }

    public void setIncorrect_answers(String[] incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }


//endregion
}
