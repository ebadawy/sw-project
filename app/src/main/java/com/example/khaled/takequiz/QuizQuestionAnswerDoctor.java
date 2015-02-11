package com.example.khaled.takequiz;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rest.model.Choice;
import com.rest.model.Question;
import com.rest.model.Quiz;
import com.rest.model.QuizWrapper;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit.Callback;
import retrofit.RetrofitError;


public class QuizQuestionAnswerDoctor extends FragmentActivity implements View.OnClickListener , OnPageChangeListener {
    Button addQuestion;
    private PagerAdapter mPagerAdapter;
    public List<Fragment> fragments;
    public List<Fragment1> createdFragments = new ArrayList<Fragment1>();
    Button nextPage;
    Button previousPage;
    Button save;
    ViewPager pager;
    int currentPage;
    EditText pageNumber;
    TextView quizName;
    Button go;
    TextView viewSubject;
    String deadline;
    String timelimit;
    String quizmark;
    String j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question_answer_doctor);
        viewSubject = (TextView) findViewById(R.id.viewSubject);
        addQuestion = (Button) findViewById(R.id.addQuestion);
        nextPage = (Button) findViewById(R.id.nextPage);
        previousPage = (Button) findViewById(R.id.previousPage);
        go = (Button) findViewById(R.id.go);
        pageNumber = (EditText) findViewById(R.id.pageNumber);
        save = (Button) findViewById(R.id.save);
        addQuestion.setOnClickListener(this);
        save.setOnClickListener(this);
        initialisePaging();
        nextPage.setOnClickListener(this);
        previousPage.setOnClickListener(this);
        go.setOnClickListener(this);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                j = null;
                deadline = null;
                timelimit = null;
                quizmark = null;
            } else {
                j = extras.getString("subject");
                viewSubject.setText(j);
                deadline = extras.getString("deadline");
                timelimit = extras.getString("timelimit");
                quizmark = extras.getString("quizmark");
            }
        } else {
            j = (String) savedInstanceState.getSerializable("subject");
            deadline = (String) savedInstanceState.getSerializable("deadline");
            timelimit = (String) savedInstanceState.getSerializable("timelimit");
            quizmark = (String) savedInstanceState.getSerializable("quizmark");
        }


    }

    private void initialisePaging() {
        fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, Fragment1.class.getName()));

        mPagerAdapter = new PagerAdapter(this.getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(mPagerAdapter);
        pager.setOnPageChangeListener(this);


    }

    private void addQuestionClick() {
        fragments.add(Fragment.instantiate(this, Fragment1.class.getName()));
        mPagerAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Question Added Swipe", Toast.LENGTH_SHORT).show();

    }

    private void goClick() {
        String pageNumber_ = pageNumber.getText().toString();
        int pageNumberx = Integer.parseInt(pageNumber_);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setCurrentItem(pageNumberx - 1, true);

    }

    public void saveClick() {
        List<String> questions_ = new ArrayList<String>();
        List<Question> questions = new ArrayList<Question>();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment1 f = (Fragment1) fragments.get(i);
            createdFragments.add(f);

        }
        for (int i = 0; i < createdFragments.size(); i++) {
            List<String> Choices_ = new ArrayList<String>();
            Choices_ = createdFragments.get(i).getChoiceList();
            questions_.add(createdFragments.get(i).getQuestion());
            System.out.println(questions_.get(i));
            List<Choice> choices = new ArrayList<Choice>();
            for (int j = 0; j < Choices_.size(); j++) {
                Choice c1 = new Choice(Choices_.get(j));
                choices.add(c1);
                System.out.println(Choices_.get(j));
            }

            Question q1 = new Question(questions_.get(i),
                    createdFragments.get(i).getRightChoice(), choices);
            questions.add(q1);
        }
        Quiz quiz = new Quiz(j, deadline, timelimit, Integer.parseInt(quizmark), questions);

        QuizWrapper quizWrapper = new QuizWrapper(quiz);

        MainActivity.api.createQuize(quizWrapper, new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getApplicationContext(), "Saving Failed", Toast.LENGTH_SHORT).show();
            }
        });

        questions_.clear();
        createdFragments.clear();


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addQuestion:
                addQuestionClick();
                break;

            case R.id.nextPage:
                nextPage();
                break;
            case R.id.previousPage:
                previousPage();
                break;
            case R.id.go:
                goClick();
                break;
            case R.id.save:
                saveClick();
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(getApplicationContext(), "Question " + (position + 1), Toast.LENGTH_SHORT).show();
        currentPage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void nextPage() {

        int totalPages = mPagerAdapter.getCount();

        int nextPage = currentPage + 1;
        if (nextPage >= totalPages) {
            // We can't go forward anymore.
            // Loop to the first page. If you don't want looping just
            // return here.
            nextPage = 0;
        }
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setCurrentItem(nextPage, true);
    }

    private void previousPage() {


        int totalPages = mPagerAdapter.getCount();

        int previousPage = currentPage - 1;
        if (previousPage < 0) {
            // We can't go back anymore.
            // Loop to the last page. If you don't want looping just
            // return here.
            previousPage = totalPages - 1;
        }
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setCurrentItem(previousPage, true);
    }


}
