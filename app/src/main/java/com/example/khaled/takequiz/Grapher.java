package com.example.khaled.takequiz;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

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
                try {
                    System.out.println("success");
                    GraphView graph = (GraphView) findViewById(R.id.graph);
                    LinearLayout l = (LinearLayout) findViewById(R.id.layout1);

                    graph.addSeries(new LineGraphSeries(graphBuilder.generateData()));
                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                    String[] quizName = graphBuilder.quizNamesArranged();
                    staticLabelsFormatter.setHorizontalLabels(quizName);
                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graph.setTitle(username);
                    graph.getGridLabelRenderer().setVerticalAxisTitle("Results");
                    graph.getGridLabelRenderer().setHorizontalAxisTitle("Quiz Names");
                    graph.getGridLabelRenderer().setVerticalAxisTitleColor(Color.BLUE);
                    graph.setTitleTextSize(40);
                    graph.setTitleColor(Color.BLUE);
                    graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(40);
                    graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(40);
                    graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.BLUE);
                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getViewport().setMinY(0);
                    graph.onDataChanged(false, false);
                    graph.getViewport().setScalable(true);

                    System.out.println("success");
                }
                catch(IllegalStateException e){
                    Intent intent = new Intent(Grapher.this,Statistics.class);
                    Toast.makeText(Grapher.this,"This student took no quizzes",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                System.out.println("fail");
                Log.e("Error","retrofit", retrofitError);


            }
        });








       // for(int i = 0 ; i<quizzes.size();i++){
       //     System.out.println(quizzes.get(i).getId()+"        "+quizresult.get(i).getResult());
       // }
    }

    }

