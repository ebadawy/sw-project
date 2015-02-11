package com.example.hussein.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class QuizActivity extends ActionBarActivity {

    int next = 0;
    int []id = new int[10];
    int num = 10;
    int Qn=0;
    int[] answers = new int[num];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        for (int i =0;i<num;i++)
            answers[i]=0;
        int [] num2 = {3,4,5};
        int [] numOfques = new int[num];
        int numOfchoices = num2[0];
        RadioButton [] choice = new RadioButton[numOfchoices];
        RadioGroup choices=(RadioGroup)findViewById(R.id.radioGroup1);
        for(int i=0;i<numOfchoices;i++){
            choice[i] =  new RadioButton(this);
            choice[i].setText("hhh");
            choice[i].setId(i+1);
            id[i]=choice[i].getId();
            choices.addView(choice[i]);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
   public void nextques(View v)
    {



        int [] num2 = {3,4,5};
        int [] numOfques = new int[num];
        int numOfchoices = num2[next];
        RadioButton [] choice = new RadioButton[numOfchoices];
        RadioGroup choices=(RadioGroup)findViewById(R.id.radioGroup1);
        for(int i=0;i<numOfchoices;i++){
            choice[i] =  (RadioButton)findViewById(id[i]);
            if(choice[i].isChecked()==true)
                answers[Qn]=id[i];
            choices.removeView(findViewById(id[i]));
            }


        next=next+1;
        numOfchoices = num2[next];
        choice = new RadioButton[numOfchoices];
        if(next<3){
            for(int i=0;i<numOfchoices;i++){
                choice[i] =  new RadioButton(this);
                choice[i].setText("hhh");
                choice[i].setId(i+1);
                id[i]= choice[i].getId();
                choices.addView(choice[i]);
                Qn++;}
            }
         }
        public  void previousques(View v)
        {
        int [] num2 = {3,4,5};
        int [] numOfques = new int[num];
        int numOfchoices = num2[next];
        RadioButton [] choice = new RadioButton[numOfchoices];
        RadioGroup choices=(RadioGroup)findViewById(R.id.radioGroup1);
        for(int i=0;i<numOfchoices;i++){
            choice[i] =  (RadioButton)findViewById(id[i]);
            choices.removeView(findViewById(id[i]));
        }


        next=next-1;
        numOfchoices = num2[next];
        choice = new RadioButton[numOfchoices];
        if(next>0){
            for(int i=0;i<numOfchoices;i++){
                choice[i] =  new RadioButton(this);
                choice[i].setText("hhh");
                choice[i].setId(i+1);
                id[i]= choice[i].getId();
                choices.addView(choice[i]);
                }
            }
        }
        public void send (View v)
        {
            for(int i=0;i<num;i++) {
                if (answers[i] == 0) {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.activity_quiz_warning);
                    dialog.show();
                }
            }

        }
    }
