package com.rest.model;

public class Choice {
    private int id;
    private String text;

    public Choice() {
        this.text = null;
        this.id = 0;
    }

    public Choice(String choice) {
        this.text = choice;
    }

    public String getChoice() {
        return this.text;
    }

    public void setChoice(String choice) {
        this.text = choice;
    }

    public int getId() {
        return this.id;
    }
}
