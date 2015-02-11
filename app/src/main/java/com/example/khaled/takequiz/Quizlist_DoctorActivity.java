package com.example.khaled.takequiz;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.rest.model.Quiz;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Quizlist_DoctorActivity extends ListActivity {
    QuizAPI api;
    List<Quiz> q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlist__doctor);

        api.getQuizzes(24,new Callback<List<Quiz>>() {
            @Override
            public void success(List<Quiz> quizs, Response response) {

                q = quizs;
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                q = null;
            }
        });

    }

    ArrayAdapter<Quiz> adapter = new ArrayAdapter<Quiz>(this,android.
            R.layout.simple_list_item_1,q);
            setListAdapter(adapter);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quizlist__doctor, menu);
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
