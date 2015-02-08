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


public class QuizActivity extends FragmentActivity implements View.OnClickListener , OnPageChangeListener{
    Button submit;
    private QuizPageAdapter mPagerAdapter;
    private List<Fragment> fragments;
    Button nextPage;
    Button previousPage;
    ViewPager pager;
    int currentPage;
    EditText pageNumber;
    TextView quizName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        submit =(Button)findViewById(R.id.submit);
        nextPage=(Button)findViewById(R.id.nextPage);
        previousPage=(Button)findViewById(R.id.previousPage);
        pageNumber = (EditText)findViewById(R.id.pageNumber);
        initialisePaging();
        nextPage.setOnClickListener(this);
        previousPage.setOnClickListener(this);
        quizName = (TextView) findViewById(R.id.quizName);
        quizName.setText(getIntent().getExtras().getString("Quiz Name"));

    }

    private void initialisePaging() {
        fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this,FragmentQuiz.class.getName()));

        mPagerAdapter = new QuizPageAdapter(this.getSupportFragmentManager(),fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(mPagerAdapter);
        pager.setOnPageChangeListener(this);


    }


    public void onClick(View view) {
        switch(view.getId()){
            case R.id.submit:
                send();
                break;

            case R.id.nextPage:
                nextPage();
                break;
            case R.id.previousPage:
                previousPage();
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void nextPage() {

        int totalPages = mPagerAdapter.getCount();

        int nextPage = currentPage+1;
        if (nextPage >= totalPages) {
            nextPage = 0;
        }
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setCurrentItem(nextPage, true);
    }

    private void previousPage() {


        int totalPages = mPagerAdapter.getCount();

        int previousPage = currentPage-1;
        if (previousPage < 0) {
            previousPage = totalPages - 1;
        }
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setCurrentItem(previousPage,true);
    }
}