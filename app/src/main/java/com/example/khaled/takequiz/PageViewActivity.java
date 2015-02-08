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
import java.util.ArrayList;
import java.util.Vector;



public class PageViewActivity extends FragmentActivity {

    MyPageAdapter pageAdapter;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_page_view);

        List<Fragment> fragments = getFragments();

        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);

        pager.setAdapter(pageAdapter);

    }
    private List<Fragment> getFragments(){

        List<Fragment> fList = new ArrayList<Fragment>();



        fList.add(MyFragment.newInstance("Fragment 1"));

        fList.add(MyFragment.newInstance("Fragment 2"));

        fList.add(MyFragment.newInstance("Fragment 3"));



        return fList;

    }


}
