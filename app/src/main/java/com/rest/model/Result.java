package com.rest.model;

import com.example.khaled.takequiz.MainActivity;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Result {
    private int result;

    public Result() {
        this.result = 0;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int rslt) {
        this.result = rslt;
    }

}
