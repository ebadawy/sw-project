package com.rest.service;

import com.example.khaled.takequiz.QuizAPI;
import com.example.khaled.takequiz.User;

import java.util.List;
import java.util.concurrent.Callable;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class RestClient {
    private static QuizAPI REST_CLIENT;
    private static String ROOT =
            "https://api-quiz-dev.herokuapp.com";

    static {
        setupRestClient();
    }

    private RestClient() {}

    public static QuizAPI get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .build();
        REST_CLIENT = adapter.create(QuizAPI.class);
    }
}
