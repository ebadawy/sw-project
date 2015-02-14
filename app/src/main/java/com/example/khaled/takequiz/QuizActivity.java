package com.example.khaled.takequiz;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.rest.model.Answer;
import com.rest.model.Choice;
import com.rest.model.CurrentQuiz;
import com.rest.model.Question;
import com.rest.model.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class QuizActivity extends FragmentActivity {
    TextView quizName;
    ToggleButton timer;
    String remainingTime;
    List<RadioGroup> radioGroups;
    List<Question> QUES;
    Chronometer chronometer;
    Quiz quiz;
    LinearLayout layout;
    int limit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        layout = (LinearLayout)findViewById(R.id.viewpager);
        quiz = CurrentQuiz.getInstance();
        quizName = (TextView) findViewById(R.id.quizName);
        quizName.setText(quiz.getName());
        timer = (ToggleButton)findViewById(R.id.timer);
        chronometer = (Chronometer)findViewById(R.id.chronometer);
        radioGroups = new ArrayList<RadioGroup>();
        QUES = new ArrayList<Question>();
        initialisePaging();
        chronometer.start();
        limit = 1000*60*(Integer.parseInt(quiz.getTime_limit().substring(3)));
        new CountDownTimer(limit, 1000) {
            public void onTick(long millisUntilFinished) {
                remainingTime = "minutes remaining: " + millisUntilFinished /(60* 1000);
                timer.setText(remainingTime);
            }
            public void onFinish() {
                timer.setText("done!");
                upload();
            }
        }.start();
    }
    private void initialisePaging() {
       MainActivity.api.getQuizQuestions(quiz.getId(),new Callback<List<Question>>() {
            @Override
            public void success(List<Question> questions, Response response) {
                int i =0;
                for(Question question : questions) {
                    TextView t = new TextView(QuizActivity.this);
                    final RadioGroup r = new RadioGroup(QuizActivity.this);
                    t.setText(question.getQuestion());
                    t.setPadding(20, 20, 20, 10);
                    layout.addView(t);
                    MainActivity.api.getQuestionChoices(quiz.getId(), question.getId(), new Callback<List<Choice>>() {
                        @Override
                        public void success(List<Choice> choices, Response response) {
                            for (Choice choice : choices) {
                                final RadioButton c = new RadioButton(QuizActivity.this);
                                c.setText(choice.getChoice());
                                c.setPadding(20, 20, 20, 10);
                                r.addView(c);
                            }
                        }
                        @Override
                        public void failure(RetrofitError retrofitError) {

                        }
                    });
                    QUES.add(question);
                    radioGroups.add(r);
                    layout.addView(radioGroups.get(i));
                    i++;
                }
                Button submit = new Button(QuizActivity.this);
                submit.setText("Submit");
                submit.setPadding(20,20,20,10);
                layout.addView(submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i=0;i<radioGroups.size();i++)
                        {
                            if(radioGroups.get(i).getCheckedRadioButtonId() == -1)
                            {
                                AlertDialog alertDialog = new AlertDialog.Builder(QuizActivity.this).create();
                                alertDialog.setTitle("WARNING");
                                alertDialog.setMessage("You haven't answered all Questions in the Quiz");
                                alertDialog.setButton("Back", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alertDialog.show();
                            }
                            else
                                upload();
                        }
                    }
                });
            }
            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }
    public void upload()
    {
        for(int i=0;i<radioGroups.size();i++)
        {
            int id= radioGroups.get(i).getCheckedRadioButtonId();
            View radioButton = radioGroups.get(i).findViewById(id);
            int radioId = radioGroups.get(i).indexOfChild(radioButton);
            RadioButton ans = (RadioButton) radioGroups.get(i).getChildAt(radioId);
            Answer answer = new Answer("NotFound404");
            if(radioGroups.get(i).getCheckedRadioButtonId() != -1)
                answer = new Answer(ans.getText().toString());
            MainActivity.api.sendAnswer(MainActivity.current_user.getId(),
                    quiz.getId(),QUES.get(i).getId(),answer,new Callback<com.squareup.okhttp.Response>() {
                        @Override
                        public void success(com.squareup.okhttp.Response response, Response response2) {
                            Intent intent = new Intent(QuizActivity.this,QuizActivityFinish.class);
                            startActivity(intent);
                            Log.d("QuizActivity","###################################send");
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            //Intent intent = new Intent(QuizActivity.this,QuizActivityFinish.class);
                            //(intent);
                            Log.d("QuizActivity","$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$fail");
                            Log.e("Error", "retrofit", retrofitError);

                        }
                    });
        }
    }
    public void hide(View view)
    {
        if(timer.isChecked()==false)
      //String time = timer.getText().toString();
        //if(time == "")
            timer.setText("");

        //else if(time == remainingTime)
           // timer.setVisibility(View.GONE);
           // chronometer.setVisibility(View.GONE);
    }
}