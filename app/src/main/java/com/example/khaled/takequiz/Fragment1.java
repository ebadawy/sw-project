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


/**
 * Created by user on 2/2/2015.
 */
public class Fragment1 extends Fragment implements View.OnClickListener{
    private LinearLayout mLayout;
    private Button mButton;
    private RadioGroup radioGroup;
    long counter=1;
    List<EditText> allEds = new ArrayList<EditText>();
    List<RadioButton> allRbs = new ArrayList<RadioButton>();
    Button check;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,
     Bundle savedInstanceState)
    { View beko = (ScrollView)inflater.inflate(R.layout.fragment1_layout,
            container,false);
        mLayout = (LinearLayout) beko.findViewById(R.id.linear1);
        mButton = (Button)beko.findViewById(R.id.addChoice);
        radioGroup = (RadioGroup)beko.findViewById(R.id.radioGroup);
        check = (Button)beko.findViewById(R.id.check);
        mButton.setOnClickListener(this);
        check.setOnClickListener(this);

         return beko;
    }


    public void addChoiceclick(){

        EditText editText = new EditText(getActivity() );
        RadioButton radiobutton = new RadioButton(getActivity());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout l = (LinearLayout) new LinearLayout(getActivity());
        l.addView(radiobutton);
        l.addView(editText);
        radiobutton.setLayoutParams(p);
        editText.setLayoutParams(p);
        editText.setText("Write Choice Here");
        editText.setId((int) counter);
        counter++;
        radiobutton.setId((int) counter);
        counter++;
        allEds.add(editText);
        allRbs.add(radiobutton);
        Log.d("View", "Start");

        try{
            radioGroup.addView(l);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void checkClick(){
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getActivity(), "Choose Right Answer", Toast.LENGTH_SHORT).show();
        }



        else
        {
            Toast.makeText(getActivity(), "Check Passed", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
     public void onClick(View view) {
                switch(view.getId()){
                    case R.id.addChoice:
                        addChoiceclick();
                        break;
                    case R.id.check:
                        checkClick();
                        break;
                }

            }



}





