package com.example.khaled.takequiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rest.model.Group;
import com.rest.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class StudentRegistration extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        MainActivity.api.getGroups(MainActivity.current_user.getId(),new Callback<List<Group>>() {
            @Override
            public void success(List<Group> groups, Response response) {

                List<String> names = new ArrayList<String>();
                for (Group group : groups) {
                    names.add(group.getGroupName());
                }
                for (final Group group : groups) {
                    Spinner spinner = (Spinner) findViewById(R.id.spinner2);
                    ArrayAdapter<String> adp =
                            new ArrayAdapter<String>(StudentRegistration.this, android.R.layout.simple_list_item_1, names);
                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adp);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            MainActivity.api.getStudents(new Callback<List<User>>() {
                                @Override
                                public void success(List<User> users, Response response) {
                                    for (final User user : users) {
                                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
                                        TextView student = new TextView(StudentRegistration.this);
                                        final Switch add = new Switch(StudentRegistration.this);
                                        LinearLayout.LayoutParams para;
                                        LinearLayout.LayoutParams params;
                                        params =
                                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                                        para =
                                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                                        student.setLayoutParams(params);
                                        student.setGravity(Gravity.LEFT);
                                        student.setText(user.getUserName());
                                        linearLayout.addView(student);


                                        add.setLayoutParams(para);
                                        add.setGravity(Gravity.RIGHT);
                                        try {
                                            if (group.getUsers().contains(user)) {

                                                add.setChecked(true);
                                            } else {
                                                add.setChecked(false);
                                            }
                                        }catch (NullPointerException e){
                                            Toast.makeText(StudentRegistration.this,"This Group have no students",Toast.LENGTH_LONG).show();
                                        }
                                        add.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                if (isChecked) {
                                                    MainActivity.api.addStudent(user.getUserName(), group.getId(), new Callback<com.squareup.okhttp.Response>() {
                                                        @Override
                                                        public void success(com.squareup.okhttp.Response response, Response response2) {
                                                            Toast.makeText(StudentRegistration.this, "Student is added successfully", Toast.LENGTH_LONG).show();
                                                        }

                                                        @Override
                                                        public void failure(RetrofitError retrofitError) {
                                                            Toast.makeText(StudentRegistration.this, "Error on adding student", Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                                } else {
                                                    MainActivity.api.deleteStudent(user.getUserName(), group.getId(), new Callback<com.squareup.okhttp.Response>() {
                                                        @Override
                                                        public void success(com.squareup.okhttp.Response response, Response response2) {
                                                            Toast.makeText(StudentRegistration.this, "Student Deleted Successfully", Toast.LENGTH_LONG).show();
                                                        }

                                                        @Override
                                                        public void failure(RetrofitError retrofitError) {
                                                            Toast.makeText(StudentRegistration.this, "Error in deleting student", Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                                }

                                            }
                                        });


                                        linearLayout.addView(add);
                                    }
                                }

                                @Override
                                public void failure(RetrofitError retrofitError) {

                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

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
        getMenuInflater().inflate(R.menu.menu_student_registration, menu);
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
