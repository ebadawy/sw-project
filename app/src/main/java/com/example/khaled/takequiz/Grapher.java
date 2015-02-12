package com.example.khaled.takequiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.rest.model.Quiz;
import com.rest.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Grapher extends ActionBarActivity {
    String userid;
    String username;
    List<Quiz> quizzes = new ArrayList<>();
    List<Result> quizresult = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grapher);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                userid = null;
                username = null;

            } else {
                userid = extras.getString("id");

                username = extras.getString("username");

            }
        } else {
            userid = (String) savedInstanceState.getSerializable("id");
            username = (String) savedInstanceState.getSerializable("username");

        }
        int id = Integer.parseInt(userid);

        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
                new GraphViewData(1, 40)
                , new GraphViewData(1, 37)
                , new GraphViewData(1, 53)
                , new GraphViewData(2, 8)
                , new GraphViewData(2, 10)
                , new GraphViewData(2, 12)
                , new GraphViewData(3, 7)
                , new GraphViewData(3, 26)
                , new GraphViewData(3, 253)

        });

        GraphView graphView = new LineGraphView(
                this // context
                , "Quizzes Graph" // heading
        );
        graphView.addSeries(exampleSeries); // data

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.addView(graphView);
        MainActivity.api.getQuizzes(id, new Callback<List<Quiz>>() {
            @Override
            public void success(List<Quiz> quizs, Response response) {
                for(int i = 0 ;i <quizs.size();i++){
                    quizzes.add(quizs.get(i));
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
        for (int i = 0 ; i<quizzes.size();i++) {
            MainActivity.api.getResult(id,quizzes.get(i).getId(),new Callback<Result>() {
                @Override
                public void success(Result result, Response response) {
                    quizresult.add(result);
                    
                }

                @Override
                public void failure(RetrofitError retrofitError) {

                }
            } );
        }
    }
    }

