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
    Button submit;
    private QuizPageAdapter mPagerAdapter;
    Button nextPage;
    Button previousPage;
    ViewPager pager;
    int currentPage;
    TextView quizName;
    Button timer;
    List<Question> QUES ;
    List<String> ANS ;
    String remainingTime;
    List<RadioGroup> radioGroups;
    String ans [];
    Chronometer chronometer;
    Quiz quiz;
    LinearLayout layout;
    RadioGroup rad;
    TextView ques;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
      /*  MainActivity.api.getQuizQuestions(MainActivity.current_quiz.getId(),new Callback<List<Question>>() {
            @Override
            public void success(List<Question> questions, Response response) {


                int i =0;
                for(Question question : questions) {

                    final FragmentQuiz currentques = (FragmentQuiz) mPagerAdapter.getItem(i);
                    final RadioGroup rad = currentques.getRadioGroup();
                    final RadioButton currentchoice = currentques.getRadioButton();
                     TextView currenttext = new TextView(QuizActivity.this);
                        currenttext   =  currentques.getTextView();
                    Log.i("info","##################################################"+question.getQuestion());
                    currenttext.setText(question.getQuestion());
                    i++;
                    MainActivity.api.getQuestionChoices(MainActivity.current_quiz.getId(),question.getId(),new Callback<List<Choice>>() {
                        @Override
                        public void success(List<Choice> choices, Response response) {
                            currentchoice.setText(choices.get(0).getChoice());
                            currentques.setChoicecontainer(currentchoice);
                            for(int j=1;j<choices.size();j++)
                            {
                                RadioButton choice = new RadioButton(QuizActivity.this);
                                choice.setId(View.generateViewId());
                                choice.setText(choices.get(j).getChoice());
                                currentques.setChoicecontainer(choice);
                                rad.addView(choice);
                            }

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
        });*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //submit =(Button)findViewById(R.id.submit);
        //nextPage=(Button)findViewById(R.id.nextPage);
        //previousPage=(Button)findViewById(R.id.previousPage);
        layout = (LinearLayout)findViewById(R.id.viewpager);
        //ques = new TextView(this);
        //layout.addView(ques);
        //rad = new RadioGroup(this);
        //layout.addView(rad);
        quiz = CurrentQuiz.getInstance();
        QUES = quiz.getQuestions();
        ANS = new ArrayList<String>();
        ans = new String[10];
        quizName = (TextView) findViewById(R.id.quizName);
        quizName.setText(quiz.getName());
        timer = (Button)findViewById(R.id.timer);
        chronometer = (Chronometer)findViewById(R.id.chronometer);
        radioGroups = new ArrayList<RadioGroup>();
        initialisePaging();
        chronometer.setVisibility(View.VISIBLE);
        chronometer.start();
        new CountDownTimer(Integer.parseInt(quiz.getTime_limit()), 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
                remainingTime = "seconds remaining: " + millisUntilFinished / 1000;
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
                            int j = 0;
                            for (Choice choice : choices) {
                                final RadioButton c = new RadioButton(QuizActivity.this);
                                c.setText(choice.getChoice());
                                c.setPadding(20, 20, 20, 10);
                                c.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (c.isChecked())
                                            ANS.add(c.getText().toString());
                                        else
                                            ANS.remove(c.getText().toString());
                                    }
                                });
                                r.addView(c);
                                j++;
                            }

                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {

                        }
                    });

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
                                alertDialog.setButton("Submit Anyway", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        upload();
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
        //mPagerAdapter = new QuizPageAdapter(this.getSupportFragmentManager());
        //pager = (ViewPager)findViewById(R.id.viewpager);
        //pager.setAdapter(mPagerAdapter);
        /*int firstnum = pager.getCurrentItem();
        FragmentQuiz firstq =(FragmentQuiz) mPagerAdapter.getItem(firstnum);
        RadioGroup firstrad = firstq.getRadioGroup();
        RadioButton firstc = firstq.getRadioButton();
        TextView firsttxt = firstq.getTextView();
        int firstchoices = QUES.get(firstnum).getChoices().size();
        firsttxt.setText(QUES.get(firstnum).getQuestion());
        firstc.setText(QUES.get(firstnum).getChoices().get(0).getChoice());
        for(int i =1;i<firstchoices;i++)
        {
            RadioButton choice = new RadioButton(this);
            choice.setId(i+firstnum);
            choice.setText(QUES.get(firstnum).getChoices().get(i).getChoice());
            firstq.setChoicecontainer(i,choice);
            firstrad.addView(choice);
        }*/
      /* for (int i=0;i<10;i++)
        {
            //FragmentQuiz currentques = (FragmentQuiz) mPagerAdapter.getItem(i);
            TextView t = new TextView(this);
            RadioGroup r = new RadioGroup(this);
            r.setId(View.generateViewId());
            radioGroups.add(r);
            t.setText("fkjhkfhkfllf");
            t.setPadding(20,20,20,10);
            layout.addView(t);
            layout.addView(radioGroups.get(i));


            for(int j=0;j<3;j++)
            {

                final RadioButton choice = new RadioButton(this);
                choice.setId(i+j);
                choice.setText("add");
                choice.setPadding(20, 20, 20, 10);
                choice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(choice.isChecked())
                            ANS.add(choice.getText().toString());
                        else
                            ANS.remove(choice.getText().toString());
                    }
                });
                radioGroups.get(i).addView(choice);

            }
        }
        Button submit = new Button(this);
        submit.setText("Submit");
        submit.setPadding(20, 20, 20, 10);
        layout.addView(submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<radioGroups.size();i++)
                {
                    if(radioGroups.get(i).getCheckedRadioButtonId() == -1)
                    {
                        Dialog dialog = new Dialog(QuizActivity.this);
                        dialog.setContentView(R.layout.activity_quiz_warning);
                        dialog.show();
                    }
                    else
                        upload();
                }
            }
        });
*/

    }
    public void nextPage(View v) {

        int totalPages = MainActivity.current_quiz.getQuestions().size();
        currentPage = pager.getCurrentItem();
        int nextPage = currentPage+1;
        if (nextPage >= totalPages) {
            nextPage = 0;
        }
        /*else {
            FragmentQuiz nextques = (FragmentQuiz) mPagerAdapter.getItem(currentPage);
            TextView text = nextques.getTextView();
            RadioGroup rad = nextques.getRadioGroup();
            RadioButton first = nextques.getRadioButton();
            int numofchoices = QUES.get(nextPage).getChoices().size();
            text.setText(QUES.get(nextPage).getQuestion());
            first.setText(QUES.get(nextPage).getChoices().get(0).getChoice());
            for (int i = 1; i < numofchoices; i++) {
                RadioButton choice = new RadioButton(this);
                choice.setId(i+nextPage);
                choice.setText(QUES.get(nextPage).getChoices().get(i).getChoice());
                nextques.setChoicecontainer(i,choice);
                rad.addView(choice);
            }
        }*/
        pager.setCurrentItem(nextPage, true);
    }

    public void previousPage(View v) {

        int totalPages = QUES.size();
        int previousPage = currentPage-1;
        if (previousPage < 0) {
            previousPage = totalPages - 1;
        }
        pager.setCurrentItem(previousPage,true);
    }
    public void send(View v)
    {
      /*  for(int i=0;i<QUES.size();i++) {
            FragmentQuiz randques = (FragmentQuiz) mPagerAdapter.getItem(i);
            RadioGroup randrad = randques.getRadioGroup();
            RadioButton randans = randques.getRadioButton();
            if(randans.isChecked())
                ANS.set(i,randans.getText().toString());
            for(int j=1;j<QUES.get(i).getChoices().size();j++)
            {
                RadioButton check = new RadioButton(this);
                check = randques.getChoicecontainer().get(j);
                if(check.isChecked())
                    ANS.set(i,randans.getText().toString());
            }
        }
        for (int i=0;i<QUES.size();i++)
        {
            if(ANS.get(i)== null)
            {
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.activity_quiz_warning);
                dialog.show();
            }

        }*/

    }
    public void upload()
    {
        for(int i=0;i<radioGroups.size();i++)
        {
            int id= radioGroups.get(i).getCheckedRadioButtonId();
            View radioButton = radioGroups.get(i).findViewById(id);
            int radioId = radioGroups.get(i).indexOfChild(radioButton);
            RadioButton ans = (RadioButton) radioGroups.get(i).getChildAt(radioId);
            Answer answer = new Answer("NotFound");
            if(radioGroups.get(i).getCheckedRadioButtonId() != -1)
                answer = new Answer(ans.getText().toString());
            MainActivity.api.sendAnswer(MainActivity.current_user.getId(),
                    quiz.getId(),radioGroups.get(i).getId(),answer,new Callback<com.squareup.okhttp.Response>() {
                        @Override
                        public void success(com.squareup.okhttp.Response response, Response response2) {
                            Intent intent = new Intent(QuizActivity.this,QuizActivityFinish.class);
                            startActivity(intent);
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            Intent intent = new Intent(QuizActivity.this,QuizActivityFinish.class);
                            startActivity(intent);

                        }
                    });
        }
    }
    public void hide()
    {
      String time = timer.getText().toString();
        if(time == "")
            timer.setText(remainingTime);
        else
            timer.setText("");

    }



}