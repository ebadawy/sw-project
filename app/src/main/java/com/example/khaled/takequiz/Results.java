package com.example.khaled.takequiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rest.model.Quiz;
import com.rest.model.Result;
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
    List<TextView> Students = new ArrayList<>();
    List<TextView> Result = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

       final int idd = (MainActivity.current_user.getId());
        final LinearLayout lm = (LinearLayout) findViewById(R.id.main);

        MainActivity.api.getQuizzes(idd,new Callback<List<Quiz>>() {
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
                    public void onItemSelected(AdapterView<?> parent, View view, int position, final long id) {
                        final LinearLayout A = (LinearLayout)findViewById(R.id.AA);

                        A.removeAllViews();
                        Students.clear();
                        Result.clear();

                        String name = parent.getItemAtPosition(position).toString();
                        for(Quiz quiz : quizs){
                            if(quiz.getName().equals(name)){
                                quizid = quiz.getId();
                            }
                        }
                        MainActivity.api.getQuizUsers(quizid,new Callback<List<User>>() {
                            @Override
                            public void success(List<User> users, Response response) {


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
                                    Students.add(txt);
                                    A.addView(Students.get(0));
                                    //lm.addView(A);

                                  final TextView result = new TextView(getApplicationContext());
                                    para.setMargins(10,35,10,0);
                                    MainActivity.api.getResult(user.getId(),quizid,new Callback<Result>() {
                                        @Override
                                        public void success(Result r, Response response) {
                                          try{
                                              result.setText(Integer.toString(r.getResult()));
                                          }catch (NullPointerException e){
                                              result.setText("Not Yet");
                                          }
                                        }

                                        @Override
                                        public void failure(RetrofitError retrofitError) {

                                        }
                                    });
                                   // result.setText(user.getUserResult());
                                    result.setGravity(Gravity.RIGHT);
                                    result.setLayoutParams(para);
                                    result.setTextColor(Color.BLACK);
                                    result.setTextSize(30);
                                    Result.add(result);
                                    A.addView(Result.get(0));
                                    //lm.addView(A);



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
