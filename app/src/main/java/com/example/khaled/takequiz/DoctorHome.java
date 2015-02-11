package com.example.khaled.takequiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;




public class DoctorHome extends ActionBarActivity implements View.OnClickListener {
    Button createQuiz;
    public void opengroups(View view){
        Intent intent = new Intent(this,Grouplayout.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        createQuiz =(Button)findViewById(R.id.createQuiz);
        createQuiz.setOnClickListener(this);
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
    private void createQuizClick(){
        Intent intent = new Intent(DoctorHome.this, QuizMaininfo.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.createQuiz:
                createQuizClick();
                break;
        }

    }
}
