package com.example.khaled.takequiz;


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

import java.util.List;
import java.util.Vector;


public class QuizQuestionAnswerDoctor extends FragmentActivity implements View.OnClickListener , OnPageChangeListener{
    Button addQuestion;
    private PagerAdapter mPagerAdapter;
    private List<Fragment> fragments;
    Button nextPage;
    Button previousPage;
    ViewPager pager;
    int currentPage;
    EditText pageNumber;
    TextView quizName;
    Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question_answer_doctor);
        addQuestion =(Button)findViewById(R.id.addQuestion);
        nextPage=(Button)findViewById(R.id.nextPage);
        previousPage=(Button)findViewById(R.id.previousPage);
        go=(Button)findViewById(R.id.go);
        pageNumber = (EditText)findViewById(R.id.pageNumber);
        addQuestion.setOnClickListener(this);
        initialisePaging();
        nextPage.setOnClickListener(this);
        previousPage.setOnClickListener(this);
        go.setOnClickListener(this);
        quizName = (TextView) findViewById(R.id.quizName);
        quizName.setText(getIntent().getExtras().getString("Quiz Name"));

    }

    private void initialisePaging() {
        fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this,Fragment1.class.getName()));

        mPagerAdapter = new PagerAdapter(this.getSupportFragmentManager(),fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(mPagerAdapter);
        pager.setOnPageChangeListener(this);


    }
    private void addQuestionClick(){
        fragments.add(Fragment.instantiate(this,Fragment1.class.getName()));
        mPagerAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Question Added Swipe", Toast.LENGTH_SHORT).show();

    }
    private void goClick(){
        String pageNumber_ = pageNumber.getText().toString();
        int pageNumberx = Integer.parseInt(pageNumber_);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setCurrentItem(pageNumberx-1,true);

    }
    public void onClick(View view) {
        switch(view.getId()){
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
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(getApplicationContext(), "Question " + (position+1), Toast.LENGTH_SHORT).show();
        currentPage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void nextPage() {

        int totalPages = mPagerAdapter.getCount();

        int nextPage = currentPage+1;
        if (nextPage >= totalPages) {
            // We can't go forward anymore.
            // Loop to the first page. If you don't want looping just
            // return here.
            nextPage = 0;
        }
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setCurrentItem(nextPage, true);
    }

    private void previousPage() {


        int totalPages = mPagerAdapter.getCount();

        int previousPage = currentPage-1;
        if (previousPage < 0) {
            // We can't go back anymore.
            // Loop to the last page. If you don't want looping just
            // return here.
            previousPage = totalPages - 1;
        }
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setCurrentItem(previousPage,true);
    }
}
