package com.example.khaled.takequiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.rest.model.Quiz;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Results extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        String DrId = intent.getStringExtra(DoctorHome.doctorid);
        Log.i("info","******************************************************"+DrId);
        int id = Integer.parseInt(DrId);
        MainActivity.api.getQuizzes(id,new Callback<List<Quiz>>() {
            @Override
            public void success(List<Quiz> quizs, Response response) {
                List<String> names = new ArrayList<String>();
                for (Quiz quiz : quizs){
                    names.add(quiz.getName());
                }

                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<String> adp = new ArrayAdapter<String>(Results.this,android.R.layout.simple_list_item_1,names);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adp);

            }

            @Override
            public void failure(RetrofitError retrofitError) {
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
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
