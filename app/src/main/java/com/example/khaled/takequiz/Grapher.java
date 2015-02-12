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
    List<Quiz> quizzes = new ArrayList<>();
    List<Result> quizresult = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grapher);
        GraphView graph = (GraphView)findViewById(R.id.graph);
        LinearLayout l = (LinearLayout)findViewById(R.id.layout1);
            Bundle extras = getIntent().getExtras();
                userid = extras.getString("id");
                username = extras.getString("username");
        int id = Integer.parseInt(userid);
        MainActivity.api.getQuizzes(id, new Callback<List<Quiz>>() {
            @Override
            public void success(List<Quiz> quizs, Response response) {
                for (int i = 0; i < quizs.size(); i++) {
                    quizzes.add(i,quizs.get(i));
                }
            }
            @Override
            public void failure(RetrofitError retrofitError) {
            }
        });
        System.out.println("quizzes size: " +quizzes.size());
        //for (int i = 0; i < quizzes.size(); i++) {
            //MainActivity.api.getResult(id, quizzes.get(i).getId(), new Callback<Result>() {
               // @Override
               // public void success(Result result, Response response) {
                  //  quizresult.add(result);

               // }

               // @Override
              //  public void failure(RetrofitError retrofitError) {

             //   }
           // });
        //}


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
    private DataPoint[] generateData() {


    int count = quizzes.size();
    DataPoint[] values = new DataPoint[count];
    for (int i = 0; i < count; i++) {

        int x = quizzes.get(i).getId();
        int y = quizresult.get(i).getResult();
        System.out.println(x);
        System.out.println(y);
        DataPoint v = new DataPoint(x, y);
        values[i] = v;

    }
    return values;
}
    }

