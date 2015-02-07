package com.rest.model;

public class Choice {
    private String text;

    public Choice() {
        this.text = null;
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
}
