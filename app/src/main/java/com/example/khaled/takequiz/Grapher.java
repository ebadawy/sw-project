package com.example.khaled.takequiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jjoe64.graphview.GraphView;

import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grapher);
        GraphView graph = (GraphView)findViewById(R.id.graph);
        LinearLayout l = (LinearLayout)findViewById(R.id.layout1);
            Bundle extras = getIntent().getExtras();
                userid = extras.getString("id");
                username = extras.getString("username");
        final int id = Integer.parseInt(userid);
        MainActivity.api.getQuizzes(id, new Callback<List<Quiz>>() {
            @Override
            public void success(final List<Quiz> quizs, Response response) {
                final int[] counter = {0};
                for (final Quiz q:quizs) {
                    final DataPoint[][] datas = {new DataPoint[quizs.size()]};

                    MainActivity.api.getResult(id, q.getId(), new Callback<Result>() {
                        @Override
                        public void success(Result result, Response response) {
                            datas[0][counter[0]]  = generateData(q,result) ;
                            counter[0]++;

                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {

                        }
                    });
                }
            }
            @Override
            public void failure(RetrofitError retrofitError) {
            }
        });





        //graph.addSeries(new LineGraphSeries(generateData()));
        //StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        //String[] quizName = new String[quizzes.size()];
        //for(int i =0 ; i< quizzes.size();i++){
         //   String name = quizzes.get(i).getName();

          //  quizName[i] = name;
        //}
        //staticLabelsFormatter.setHorizontalLabels(quizName);
        //graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        //l.addView(graph);
       // for(int i = 0 ; i<quizzes.size();i++){
       //     System.out.println(quizzes.get(i).getId()+"        "+quizresult.get(i).getResult());
       // }
    }
    private DataPoint generateData(Quiz quiz, Result result) {
        int x = quiz.getId();
        int y = result.getResult();
        DataPoint v = new DataPoint(x, y);
    return v;
}
    }

