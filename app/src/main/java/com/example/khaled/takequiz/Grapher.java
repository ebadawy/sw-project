package com.example.khaled.takequiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
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
import com.rest.model.GraphBuilder;
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

            Bundle extras = getIntent().getExtras();
                userid = extras.getString("id");
                username = extras.getString("username");
        final int id = Integer.parseInt(userid);
        MainActivity.api.graphPoints(id, new Callback<GraphBuilder>() {

            @Override
            public void success(GraphBuilder graphBuilder, Response response) {
                System.out.println("success");
                GraphView graph = (GraphView)findViewById(R.id.graph);
                LinearLayout l = (LinearLayout)findViewById(R.id.layout1);

                graph.addSeries(new LineGraphSeries( graphBuilder.generateData()));
                l.addView(graph);
                System.out.println("success");
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                System.out.println("fail");

            }
        });





        //StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        //String[] quizName = new String[quizzes.size()];
        //for(int i =0 ; i< quizzes.size();i++){
         //   String name = quizzes.get(i).getName();

          //  quizName[i] = name;
        //}
        //staticLabelsFormatter.setHorizontalLabels(quizName);
        //graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

       // for(int i = 0 ; i<quizzes.size();i++){
       //     System.out.println(quizzes.get(i).getId()+"        "+quizresult.get(i).getResult());
       // }
    }

    }

