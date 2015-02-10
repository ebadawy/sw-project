package com.example.khaled.takequiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz);
        final TextView txt = (TextView) findViewById(R.id.txt);
        try {
            api.getQuizzes(new Callback<List<Quiz>>() {
                @Override
                public void success(List<Quiz> quizs, Response response) {
                    txt.setText(quiz.getName());
                }

                @Override
                public void failure(RetrofitError retrofitError) {

                }
            });
        }catch (NullPointerException e){
            txt.setText("No Quizes :D");
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
