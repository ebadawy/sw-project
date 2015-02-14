package com.rest.model;

public class Answer {
    private String answer;
    private boolean correct;
    private int user_id;
    private int quiz_id;
    private int question_id;
    public Answer(String answer, int user_id, int quiz_id, int question_id) {
        this.answer = answer;
        this.correct = false;
        this.user_id = user_id;
        this.quiz_id = quiz_id;
        this.question_id = question_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.user_id = user_id;
    }

    public void setQuestion_id(int question_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public int getQuiz_id() {
        return this.quiz_id;
    }

    public int getQuestion_id() {
        return this.question_id;
    }


}
