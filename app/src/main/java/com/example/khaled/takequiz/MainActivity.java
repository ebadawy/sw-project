package com.example.khaled.takequiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    public void login(View view){
        Intent intent = new Intent(this,StudentHome.class);
        Intent intent1 = new Intent(this,DoctorHome.class);
        EditText editText = (EditText)findViewById(R.id.Enter_ID);
        EditText editText1 = (EditText)findViewById(R.id.Enter_password);
        String idnumber = editText.getText().toString();
        String password = editText1.getText().toString();
        if(idnumber.equals("4444")&&password.equals("4444")){
            startActivity(intent);
        }else if(idnumber.equals("1111")&&password.equals("1111")){
            startActivity(intent1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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