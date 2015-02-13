package com.example.khaled.takequiz;

import com.rest.model.Answer;
import com.rest.model.Choice;
import com.rest.model.GraphBuilder;
import com.rest.model.Group;
import com.rest.model.Question;
import com.rest.model.QuestionWrapper;
import com.rest.model.Quiz;
import com.rest.model.QuizWrapper;
import com.rest.model.Result;
import com.rest.model.User;
import com.rest.model.UserWrapper;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Response;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.PUT;
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

    @GET("/users/{user_id}/quizzes")
    public void getQuizzes(@Path("user_id") int userId, Callback<List<Quiz>> quizzes);

    @GET("/quizzes/{quiz_id}/questions")
    public void getQuizQuestions(@Path("quiz_id") int quiz_id,
                                 Callback<List<Question>> questions);

    @GET("/quizzes/{quiz_id}/questions/{question_id}/choices")
    public void getQuestionChoices(@Path("quiz_id") int quiz_id,
                                   @Path("question_id") int question_id,
                                   Callback<List<Choice>> choices);

    @POST("/users/{user_id}/quizzes/{quiz_id}/questions/{question_id}/answers")
    public void sendAnswer(@Path("user_id") int user_id,
                           @Path("quiz_id") int quiz_id,
                           @Path("question_id") int question_id,
                           @Body Answer answer,
                           Callback<Response> response);

    @GET("/users/{user_id}/quizzes/{quiz_id}/questions/{question_id}/answers")
    public void getAnswer(@Path("user_id") int user_id,
                          @Path("quiz_id") int quiz_id,
                          @Path("question_id") int question_id,
                          Callback<Answer> answer);

    @GET("/users/{user_id}/quizzes/{quiz_id}/answers")
    public void getAnswers(@Path("user_id") int user_id,
                           @Path("quiz_id") int quiz_id,
                            Callback<List<Answer>> answers);

    @GET("/users/{user_id}/quizzes/{quiz_id}/results")
    public void getResult(@Path("user_id") int user_id,
                           @Path("quiz_id") int quiz_id,
                           Callback<Result> rslt);

    @GET("/users?role=student")
    public void getStudents(Callback<List<User>> users);

    @GET("/quizzes?users={user_id}&status=true")
    public void getQuizzesWithResults(@Path("user_id") int userID,
                                      Callback<List<Quiz>> quizzes);

    @PATCH("/quizzes/{quiz_id}")
    public void quizStatus(@Path("quiz_id") int quizId,
                             @Query("quiz_status") boolean published,
                             Callback<Response> responseCallback);

    @GET("/users")
    public void getQuizUsers(@Query("quiz_id") int quizId,
                             Callback<List<User>> users);

    @PATCH("/publish")
    public void resultStatus(@Query("quiz_id") int quizId,
                             @Query("result_status") int publish,
                             Callback<Response> responseCallback);

    @GET("/graph")
    public void graphPoints(@Query("user_id") int userId,
                               Callback<GraphBuilder> graphBuilderCallback);

    @POST("/groups")
    public void createGroup(@Body Group group, @Query("user_id") int userId, Callback<Response> responseCallback);

    @DELETE("/groups/{group_id}")
    public void deleteGroup(@Path("group_id") int groupId, Callback<Response> responseCallback);

}