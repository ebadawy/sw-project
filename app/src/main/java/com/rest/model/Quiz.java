package com.rest.model;


import java.util.List;

public class Quiz {
    private int id;
    private String name;
    private List<Question> questions;

    public Quiz() {
        this.name = null;
        this.questions = null;
        this.id = 0;
    }

    public Quiz(String n, List<Question> q) {
        this.id = 0;
        this.name = n;
        this.questions = q;
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

