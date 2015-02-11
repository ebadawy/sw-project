package com.example.khaled.takequiz;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rest.model.Quiz;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SelectQuiz extends ActionBarActivity {

    public static QuizAPI api;
    Quiz quiz;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz);
        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearmain);

        LinearLayout.LayoutParams params =
        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final TextView txt = (TextView) findViewById(R.id.txt);
        try {
            api.getQuizzes(new Callback<List<Quiz>>() {
                @Override
                public void success(List<Quiz> quizs, Response response) {
                   // txt.setText(quiz.getName());
                    if(quizs.size()!=0){
                    for (int i=0;i<quizs.size();i++) {
                        //Create Layout
                        LinearLayout l1 = new LinearLayout(getApplicationContext());
                        l1.setOrientation(LinearLayout.HORIZONTAL);
                        //Create Button
                        final Button btn = new Button(getApplicationContext());
                        btn.setId(quiz.getId());
                        btn.setText(quiz.getName());
                        btn.setGravity(Gravity.LEFT);
                        //Create Deadline text
                        TextView deadline = new TextView(getApplicationContext());
                        deadline.setText(quiz.getDeadline());
                        deadline.setGravity(Gravity.RIGHT);
                    }
                    }else{
                        txt.setText("No Quizzes Available Now");
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    txt.setText("Cannot Connect To Server");
                    txt.setTextColor(Color.RED);
                }
            });
        }catch (NullPointerException e){
            txt.setText("No Quizzes Available Now");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_quiz, menu);
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
}
