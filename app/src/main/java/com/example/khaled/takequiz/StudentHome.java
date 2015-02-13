package com.example.khaled.takequiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class StudentHome extends ActionBarActivity {









    public static final String studentid="com.example.khaled.takequiz.MESSAGE";

    public void getout(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void listquiz(View view){
        Intent intent = new Intent(this,SelectQuiz.class);
        Intent n = getIntent();
        String Stuid = n.getStringExtra(MainActivity.StuID);
        intent.putExtra(studentid,Stuid);
        startActivity(intent);


    }
    public void openresults(View v){
        Intent intent = new Intent(this,studentresult.class);
        Intent n = getIntent();
        String Stuid = n.getStringExtra(MainActivity.StuID);
        intent.putExtra(studentid,Stuid);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_home, menu);
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
