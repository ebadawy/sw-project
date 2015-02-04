package com.rest.service;

import android.util.Log;

import com.example.khaled.takequiz.QuizAPI;
import retrofit.RestAdapter;

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
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String s) {
                        Log.i("retrofitRequest**", s);
                    }
                })
                .setEndpoint(ROOT)
                .build();
        REST_CLIENT = adapter.create(QuizAPI.class);
    }
}
