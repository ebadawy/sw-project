package com.example.khaled.takequiz;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.rest.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Statistics extends ActionBarActivity implements View.OnClickListener{
List<Button> StudentNames = new ArrayList<>();
LinearLayout layout1;
int counter = 0;
List<User> usersextra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        layout1=(LinearLayout)findViewById(R.id.layout1);


        MainActivity.api.getStudents(new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                for(int i = 0;i<users.size();i++){
                    Button name = new Button(getApplicationContext());
                    name.setText(users.get(i).getUserName());
                    ViewGroup.LayoutParams lp =new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    name.setLayoutParams(lp);
                    name.setId(counter);
                    StudentNames.add(name);
                    counter ++;
                    layout1.addView(name);
                    usersextra.add(users.get(i));
                }
                for(int i = 0 ;i<StudentNames.size();i++){
                    final int j = i;
                    StudentNames.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int id = usersextra.get(j).getId();
                            String username =usersextra.get(j).getUserName();
                            String ids = Integer.toString(id);
                            Bundle extras =new Bundle();
                            extras.putString("id",ids);
                            extras.putString("username",username);
                            Intent intent = new Intent(Statistics.this,Grapher.class);
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
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



    @Override
    public void onClick(View view) {

        }

    }

