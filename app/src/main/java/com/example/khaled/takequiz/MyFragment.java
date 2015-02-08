package com.example.khaled.takequiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class MyFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";



    public static final MyFragment newInstance(String message)

    {

        MyFragment f = new MyFragment();

        Bundle bdl = new Bundle(1);

        bdl.putString(EXTRA_MESSAGE, message);

        f.setArguments(bdl);

        return f;

    }



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        String message = getArguments().getString(EXTRA_MESSAGE);

        View v = inflater.inflate(R.layout.myfragment, container, false);

        TextView messageTextView = (TextView)v.findViewById(R.id.textView);

        messageTextView.setText(message);



        return v;

    }

}

