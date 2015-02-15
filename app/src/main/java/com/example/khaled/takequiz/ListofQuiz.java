package com.example.khaled.takequiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.rest.model.Quiz;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ListofQuiz extends ActionBarActivity {
    LinearLayout.LayoutParams params;
    LinearLayout.LayoutParams para;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_quiz);
        int id =MainActivity.current_user.getId();

        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearmain);
        //final LinearLayout am =(LinearLayout) findViewById(R.id.alternate);

        params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        para =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.weight=2;
        para.weight=1;


        final TextView txt = (TextView) findViewById(R.id.txt);

        try {
            MainActivity.api.getQuizzes(id, new Callback<List<Quiz>>() {
                @Override
                public void success(List<Quiz> quizs, Response response) {
                    // txt.setText(quiz.getName());
                    if (quizs.size() != 0) {
                        for ( final Quiz quiz : quizs) {
                            LinearLayout A = new LinearLayout(ListofQuiz.this);
                            final Button btn = new Button(ListofQuiz.this);
                            //final Button btn = (Button)findViewById(R.id.bt);
                            btn.setText(quiz.getName());
                            btn.setGravity(Gravity.LEFT);
                            btn.setLayoutParams(params);
                            btn.setTextColor(Color.BLACK);
                            btn.setTextSize(30);
                            btn.setBackgroundColor(Color.WHITE);

                            A.addView(btn);

                            final Switch toggleButton = new Switch(ListofQuiz.this);
                            toggleButton.setLayoutParams(para);
                            if(quiz.isPublished()) {
                                toggleButton.setChecked(true);
                            }else{
                                toggleButton.setChecked(false);
                            }
                            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if(isChecked){
                                        MainActivity.api.quizStatus(quiz.getId(),true,new Callback<com.squareup.okhttp.Response>() {
                                            @Override
                                            public void success(com.squareup.okhttp.Response response, Response response2) {
                                                Toast.makeText(ListofQuiz.this,"Quiz is now published to students",Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void failure(RetrofitError retrofitError) {
                                                Toast.makeText(ListofQuiz.this,"Error, Quiz is not published",Toast.LENGTH_LONG).show();

                                            }
                                        });
                                        toggleButton.setChecked(true);
                                    }else{
                                        MainActivity.api.quizStatus(quiz.getId(),false,new Callback<com.squareup.okhttp.Response>() {
                                            @Override
                                            public void success(com.squareup.okhttp.Response response, Response response2) {
                                                Toast.makeText(ListofQuiz.this,"Quiz is not published to students",Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void failure(RetrofitError retrofitError) {
                                                Toast.makeText(ListofQuiz.this,"Error, Quiz is still published",Toast.LENGTH_LONG).show();
                                                

                                            }
                                        });
                                        toggleButton.setChecked(false);
                                    }
                                }
                            });
                            A.addView(toggleButton);

                            final Switch result = new Switch(ListofQuiz.this);
                            result.setLayoutParams(para);
                            if(quiz.isResultPublished()){
                                result.setChecked(true);
                            }else{
                                result.setChecked(false);
                            }
                            result.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (isChecked) {
                                        MainActivity.api.resultStatus(quiz.getId(), true, new Callback<com.squareup.okhttp.Response>() {
                                            @Override
                                            public void success(com.squareup.okhttp.Response response, Response response2) {
                                                Toast.makeText(ListofQuiz.this, "Results is published to students", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void failure(RetrofitError retrofitError) {

                                                Toast.makeText(ListofQuiz.this, "Error in publishing results", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else {
                                        MainActivity.api.resultStatus(quiz.getId(), false, new Callback<com.squareup.okhttp.Response>() {
                                            @Override
                                            public void success(com.squareup.okhttp.Response response, Response response2) {

                                                Toast.makeText(ListofQuiz.this, "Results is not published to students", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void failure(RetrofitError retrofitError) {

                                                Toast.makeText(ListofQuiz.this, "Error occurred", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            });
                            A.addView(result);
                            lm.addView(A);


                        }
                    } else {
                        txt.setText("No Quizzes Available Now");
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    txt.setText(retrofitError.getMessage());
                    txt.setTextColor(Color.RED);
                    Log.e("Error", "retrofit", retrofitError);
                }
            });
        }catch (NullPointerException e){
            txt.setText("No Quizzes Available Now");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listof_quiz, menu);
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
