package com.example.khaled.takequiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;


public class QuizPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public QuizPageAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int arg0) {

        return fragments.get(arg0);
    }

    @Override
    public int getCount() {

        return this.fragments.size();
    }


}