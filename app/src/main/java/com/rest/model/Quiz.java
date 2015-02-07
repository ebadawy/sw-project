package com.rest.model;


import java.util.List;

public class Quiz {
    private String name;
    private List<Question> questions;

    public Quiz() {
        this.name = null;
        this.questions = null;
    }

    public String getName() {
        return this.name;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }
}
