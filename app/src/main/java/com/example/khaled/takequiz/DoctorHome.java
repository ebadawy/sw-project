package com.example.khaled.takequiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rest.model.Result;


public class DoctorHome extends ActionBarActivity {


    public void drresults(View v){
        Intent intent = new Intent(this, Results.class);
        Intent docid = getIntent();
        String id = docid.getStringExtra(MainActivity.DocID);
        Log.i("Info","++++++++++++++++++++++++++++++++++++++++++++++++++++++"+id);
        startActivity(intent);

    }

    Intent groupsIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
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
    public void stay(){
        Intent intent = new Intent(this,DoctorHome.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        stay();
    }


}
