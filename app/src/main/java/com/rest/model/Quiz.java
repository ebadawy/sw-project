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

