package com.example.khaled.takequiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rest.model.User;
import com.rest.service.RestClient;

import java.io.Closeable;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {
    public final static String DocID = "com.example.khaled.takequiz.id";
    public final static String StuID="com.example.khaled.takequiz.id";
    TextView logs;
    public static QuizAPI api;
    public void login(View view){
        final Intent studentActivity = new Intent(this,StudentHome.class);
        final Intent docActivity = new Intent(this,DoctorHome.class);
        EditText editText = (EditText)findViewById(R.id.Enter_ID);
        EditText editText1 = (EditText)findViewById(R.id.Enter_password);
        String userName = editText.getText().toString();
        String password = editText1.getText().toString();
        if (!userName.isEmpty() || !password.isEmpty()) {
            logs.setTextColor(Color.BLACK);
            logs.setText("Please Wait...");
            api.login(userName, password, new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    try {
                       // the user is authenticated, ur code goes here
                       if(user.getRole().equals("student")){
                           studentActivity.putExtra(StuID,Integer.toString(user.getId()));
                           startActivity(studentActivity);
                       }else if(user.getRole().equals("doc")){
                           docActivity.putExtra(DocID,Integer.toString(user.getId()));
                           startActivity(docActivity);
                       }

                    } catch(NullPointerException e) {
                       logs.setTextColor(Color.RED);
                       logs.setText("Invalid User Name or Password");
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    logs.setTextColor(Color.RED);
                    logs.setText("Error: Cannot connect to the server!");
                }
            });
        } else {
            logs.setTextColor(Color.RED);
            logs.setText("Invalid User Name or Password");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logs = (TextView) findViewById(R.id.logs);
        api = RestClient.get();
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
