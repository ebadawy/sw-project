package com.example.khaled.takequiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Groups extends ActionBarActivity implements View.OnClickListener {

    Intent srIntent;
    Button gl;
    Button sr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        gl = (Button) findViewById(R.id.groupList);
        sr = (Button) findViewById(R.id.studnetRegistation);
        srIntent = new Intent(this, StudentRegistration.class);
        gl.setOnClickListener(this);

    }

    public void startsrIntent(View v) {
        startActivity(srIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_groups, menu);
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
    public void grouplistClick(){
        Intent intent = new Intent (this,GroupList.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.groupList:
                grouplistClick();
                break;

        }

    }
}
