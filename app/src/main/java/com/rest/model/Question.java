package com.rest.model;

import java.util.List;

public class Question {
    private String name;
    private String right_answer;
    private List<Choice> choices;

    public Question() {
        this.name = null;
        this.right_answer = null;
        this.choices = null;
    }

    public String getQuestion() {
        return this.name;
    }

    public String getRight_answer() {
        return this.right_answer;
    }

    public List<Choice> getChoices() {
        return this.choices;
    }
}
