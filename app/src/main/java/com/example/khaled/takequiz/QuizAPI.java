package com.example.khaled.takequiz;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface QuizAPI {
    @POST("/users")
    @Headers({"Content-Type: application/json"})
    public void createUser(@Body RequestWraper user,
                           Callback<RequestWraper> response);

    @GET("/users")
    public void getUsers(Callback<List<User>> users);
}
