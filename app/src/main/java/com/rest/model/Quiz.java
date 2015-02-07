package com.rest.model;


import java.util.List;

public class Quiz {
    private int id;
    private String name;
    private String deadline;
    private String time_limit;
    private int quiz_mark;

    private List<Question> questions;

    public Quiz() {
        this.name = null;
        this.questions = null;
        this.id = 0;
        this.deadline = null;
        this.time_limit = null;
        this.quiz_mark = 0;
    }

    public Quiz(String name, String deadline,
                String time_limit, int quiz_mark, List<Question> questions) {
        this.id = 0;
        this.name = name;
        this.deadline = deadline;
        this.time_limit = time_limit;
        this.quiz_mark = quiz_mark;
        this.questions = questions;
    }

    public void setQuestions(List<Question> q) {
        this.questions = q;
    }
    public String getName() {
        return this.name;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public int getId() {
        return this.id;
    }
}

