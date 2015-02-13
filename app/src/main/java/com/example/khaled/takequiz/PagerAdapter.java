package com.example.khaled.takequiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by user on 2/1/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public PagerAdapter(FragmentManager fm,List<Fragment> fragments) {
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
