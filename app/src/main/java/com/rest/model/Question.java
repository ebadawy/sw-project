package com.rest.model;

import java.util.List;

public class Question {
    private int id;
    private String name;
    private String right_answer;
    private List<Choice> choices;

    public Question() {
        this.name = null;
        this.right_answer = null;
        this.choices = null;
        this.id = 0;
    }

    public Question(String question, String rightAnswer, List<Choice> c) {
        this.name = question;
        this.right_answer = rightAnswer;
        this.choices = c;
        this.id = 0;
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

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public int getId() {
        return this.id;
    }
}
