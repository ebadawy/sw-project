package com.example.khaled.takequiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;


public class QuizPageAdapter extends FragmentPagerAdapter {
    int num = 10;

    public QuizPageAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int arg0) {

        return new FragmentQuiz();
    }

    @Override
    public int getCount() {

        return num;
    }


}