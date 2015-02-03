package com.example.khaled.takequiz;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface QuizAPI {
    @GET("/users")
    public void getUser(Callback<List<User>> respone);
}
