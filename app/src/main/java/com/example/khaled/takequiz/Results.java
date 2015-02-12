package com.example.khaled.takequiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rest.model.Quiz;
import com.rest.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Results extends ActionBarActivity {
public static int quizid;
    LinearLayout.LayoutParams params;
    LinearLayout.LayoutParams para;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        String DrId = intent.getStringExtra(DoctorHome.doctorid);
        Log.i("info","******************************************************"+DrId);
        int id = Integer.parseInt(DrId);
        final LinearLayout lm = (LinearLayout) findViewById(R.id.main);
        MainActivity.api.getQuizzes(id,new Callback<List<Quiz>>() {
            @Override
            public void success(final List<Quiz> quizs, Response response) {
                final List<String> names = new ArrayList<String>();
                for (Quiz quiz : quizs){
                    names.add(quiz.getName());
                }

                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<String> adp = new ArrayAdapter<String>(Results.this,android.R.layout.simple_list_item_1,names);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adp);
                spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String name = parent.getItemAtPosition(position).toString();
                        Log.i("info","######################################################"+name);
                        for(Quiz quiz : quizs){
                            if(quiz.getName().equals(name)){
                                quizid = quiz.getId();
                            }
                        }
                        MainActivity.api.getQuizUsers(quizid,new Callback<List<User>>() {
                            @Override
                            public void success(List<User> users, Response response) {
                                LinearLayout A = new LinearLayout(Results.this);

                                params =
                                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                para =
                                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                                for(User user : users){
                                    TextView txt = new TextView(Results.this);
                                    txt.setText(user.getUserName());
                                    txt.setGravity(Gravity.LEFT);
                                    txt.setLayoutParams(params);
                                    txt.setBackgroundColor(Color.WHITE);
                                    txt.setTextColor(Color.BLACK);
                                    txt.setTextSize(30);
                                    A.addView(txt);

                                    TextView result = new TextView(getApplicationContext());
                                    para.setMargins(10,35,10,0);
                                    result.setText(user.getUserResult());
                                    result.setGravity(Gravity.RIGHT);
                                    result.setLayoutParams(para);
                                    result.setTextColor(Color.BLACK);
                                    result.setTextSize(30);
                                    A.addView(result);


                                    lm.addView(A);
                                }
                            }

                            @Override
                            public void failure(RetrofitError retrofitError) {
                                Toast.makeText(Results.this,"No Student Took This Quiz Yet",Toast.LENGTH_LONG).show();
                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void failure(RetrofitError retrofitError) {
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
