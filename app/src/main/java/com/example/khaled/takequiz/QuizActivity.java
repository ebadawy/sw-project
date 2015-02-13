package com.example.khaled.takequiz;


import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.rest.model.Question;
import com.rest.model.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class QuizActivity extends FragmentActivity {
    Button submit;
    private QuizPageAdapter mPagerAdapter;
    Button nextPage;
    Button previousPage;
    ViewPager pager;
    int currentPage;
    TextView quizName;
    List<Question> QUES ;
    List<String> ANS ;
    Chronometer chronometer;
    Quiz quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        submit =(Button)findViewById(R.id.submit);
        nextPage=(Button)findViewById(R.id.nextPage);
        previousPage=(Button)findViewById(R.id.previousPage);
        quiz = MainActivity.current_quiz;
        initialisePaging();
        QUES = quiz.getQuestions();
        ANS = new ArrayList<String>();
        quizName = (TextView) findViewById(R.id.quizName);
        //quizName.setText(getIntent().getExtras().getString("Quiz Name"));
        chronometer = (Chronometer)findViewById(R.id.chronometer);
        chronometer.setVisibility(View.VISIBLE);
        chronometer.start();

    }

    private void initialisePaging() {
        mPagerAdapter = new QuizPageAdapter(this.getSupportFragmentManager());
        pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(mPagerAdapter);
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
        for (int i=0;i<quiz.getQuestions().size();i++)
        {
            FragmentQuiz currentques = (FragmentQuiz) mPagerAdapter.getItem(i);
            RadioGroup rad = currentques.getRadioGroup();
            RadioButton currentchoice = currentques.getRadioButton();
            TextView currenttext = currentques.getTextView();
            int choices = quiz.getQuestions().get(i).getChoices().size();
            currenttext.setText(quiz.getQuestions().get(i).getQuestion());
            currentchoice.setText(quiz.getQuestions().get(i).getChoices().get(0).getChoice());
            for(int j=1;j<choices;j++)
            {
                RadioButton choice = new RadioButton(this);
                choice.setId(i+j);
                choice.setText(quiz.getQuestions().get(i).getChoices().get(j).getChoice());
                currentques.setChoicecontainer(choice);
                rad.addView(choice);
            }
        }

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
    public void upload(View v)
    {

    }
    public void hide()
    {
      /*  if(chronometer.getVisibility()==View.VISIBLE)
            chronometer.setVisibility(View.GONE);
        if(chronometer.getVisibility()==View.GONE)
            chronometer.setVisibility(View.VISIBLE);*/
    }


}