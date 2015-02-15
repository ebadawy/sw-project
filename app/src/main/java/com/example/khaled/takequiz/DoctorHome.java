package com.example.khaled.takequiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class DoctorHome extends ActionBarActivity implements View.OnClickListener {
    Button statistics;
    public static final String doctorid="com.example.khaled.takequiz.MESSAGE";






    Button createQuiz;
    public void opengroups(View view) {
        Intent intent = new Intent(this, Grouplayout.class);
    }

    public void drresults(View v){
        Intent intent = new Intent(this, Results.class);
        Intent docid = getIntent();
        String id = docid.getStringExtra(MainActivity.DocID);
        Log.i("Info","++++++++++++++++++++++++++++++++++++++++++++++++++++++"+id);
        intent.putExtra(doctorid,id);


        startActivity(intent);

    }

    public void listofquiz(View v){
        Intent intent = new Intent(this,ListofQuiz.class);
        Intent docid = getIntent();
        String id = docid.getStringExtra(MainActivity.DocID);
        intent.putExtra(doctorid,id);

        startActivity(intent);
    }
    public void stata(View v){
//        Intent intent = new Intent(this, Statistics.class);
//        startActivity(intent);
    }


    Intent groupsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        createQuiz =(Button)findViewById(R.id.createQuiz);
        createQuiz.setOnClickListener(this);
        statistics =(Button)findViewById(R.id.statistics);
        statistics.setOnClickListener(this);

        groupsIntent = new Intent(this, Groups.class);

    }

    public void startGroupsIntent(View v) {
        startActivity(groupsIntent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor_home, menu);
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

    public void statisticsClick(){
        Intent intent = new Intent(DoctorHome.this,Statistics.class);
        startActivity(intent);

    }


    private void createQuizClick(){
        Intent intent = new Intent(DoctorHome.this, QuizMaininfo.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.statistics:
                statisticsClick();
                break;
            case R.id.createQuiz:
                createQuizClick();
                break;
        }

    }

    
}
