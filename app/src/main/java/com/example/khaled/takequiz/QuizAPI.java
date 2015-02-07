package com.example.khaled.takequiz;

import com.rest.model.Question;
import com.rest.model.QuestionWraper;
import com.rest.model.User;
import com.rest.model.UserWraper;
import com.squareup.okhttp.Response;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface QuizAPI {

    @POST("/users")
    @Headers({"Content-Type: application/json"})
    public void createUser(@Body UserWraper user,
                           Callback<Response> response);


    @GET("/users")
    public void getUsers(Callback<List<User>> users);

    @GET("/login")
    public void login(@Query("user_name") String userName,
                      @Query("password") String pass,
                      Callback<User> user);

    @POST("/questions")
    public void createQuestion(@Body QuestionWraper questionWraper,
                                Callback<Response> response);
}
