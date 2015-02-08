package com.example.khaled.takequiz;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;


/**
 * Created by Hussein on 08/02/2015.
 */
class MyPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;


    public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {

        super(fm);

        this.fragments = fragments;

    }

    @Override

    public Fragment getItem(int position) {

        return this.fragments.get(position);

    }


    @Override

    public int getCount() {

        return this.fragments.size();

    }

}
