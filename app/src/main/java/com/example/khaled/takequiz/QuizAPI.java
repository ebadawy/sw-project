package com.example.khaled.takequiz;

import com.rest.model.Choice;
import com.rest.model.Question;
import com.rest.model.QuestionWrapper;
import com.rest.model.Quiz;
import com.rest.model.QuizWrapper;
import com.rest.model.User;
import com.rest.model.UserWrapper;
import com.squareup.okhttp.Response;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface QuizAPI {

    @POST("/users")
    @Headers({"Content-Type: application/json"})
    public void createUser(@Body UserWrapper user,
                           Callback<Response> response);

    @GET("/users")
    public void getUsers(Callback<List<User>> users);

    @GET("/login")
    public void login(@Query("user_name") String userName,
                      @Query("password") String pass,
                      Callback<User> user);

    @POST("/questions")
    public void createQuestion(@Body QuestionWrapper questionWraper,
                               Callback<Response> response);

    @POST("/quizzes")
    public void createQuize(@Body QuizWrapper quizWrapper,
                            Callback<Response> response);

    @GET("/quizzes")
    public void getQuizzes(Callback<List<Quiz>> quizzes);

    @GET("/quizzes/{quiz_id}/questions")
    public void getQuizQuestions(@Path("quiz_id") int quiz_id,
                                 Callback<List<Question>> questions);

    @GET("/quizzes/{quiz_id}/questions/{question_id}/choices")
    public void getQuestionChoices(@Path("quiz_id") int quiz_id,
                                   @Path("question_id") int question_id,
                                   Callback<List<Choice>> choices);



}