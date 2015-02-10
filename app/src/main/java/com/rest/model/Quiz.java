package com.rest.model;


import android.widget.Toast;

import com.example.khaled.takequiz.MainActivity;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Quiz {
    private int id;
    private String name;
    private String deadline;
    private String time_limit;
    private int quiz_mark;
    private List<Question> questions;
    private boolean published;

    public Quiz() {
        this.name = null;
        this.questions = null;
        this.id = 0;
        this.deadline = null;
        this.time_limit = null;
        this.quiz_mark = 0;
        this.published = false;
    }

    public Quiz(String name, String deadline,
                String time_limit, int quiz_mark, List<Question> questions) {
        this.id = 0;
        this.name = name;
        this.deadline = deadline;
        this.time_limit = time_limit;
        this.quiz_mark = quiz_mark;
        this.questions = questions;
        this.published = false;
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

    public boolean isPublished() {return published;}

    public void loadQuizInfo() {

        MainActivity.api.getQuizQuestions(getId(), new Callback<List<Question>>() {
            @Override
            public void success(List<Question> questions, Response response) {
                for(final Question question : questions) {
                    MainActivity.api.getQuestionChoices(1, question.getId(), new Callback<List<Choice>>() {
                        @Override
                        public void success(List<Choice> choices, Response response) {
                            question.setChoices(choices);
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {

                        }
                    });
                }
                setQuestions(questions);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }
}

