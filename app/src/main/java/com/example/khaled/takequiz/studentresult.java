package com.example.khaled.takequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.rest.model.Quiz;
import com.rest.model.Result;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class studentresult extends ActionBarActivity {
    LinearLayout.LayoutParams params;
    QuizAPI api;
    Quiz quiz;
    public void pop(View view){
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setMessage("Your Result Is ");
        alertDialog.setButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                alertDialog.dismiss();
            }
        });
            alertDialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentresult);
        Intent intent = getIntent();
        String sid=intent.getStringExtra(StudentHome.studentid);
        //final int id = Integer.parseInt(sid);
        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearmain);
        //final LinearLayout am =(LinearLayout) findViewById(R.id.alternate);

        params =
              new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final TextView txt = (TextView) findViewById(R.id.txt);
        try {
            MainActivity.api.getQuizzes(MainActivity.current_user.getId(), new Callback<List<Quiz>>() {
                @Override
                public void success(List<Quiz> quizs, Response response) {
                    // txt.setText(quiz.getName());
                    if (quizs.size() != 0) {
                        for (final Quiz quiz : quizs) {
                            LinearLayout A = new LinearLayout(getApplicationContext());
                            A.setOrientation(LinearLayout.HORIZONTAL);
                            final Button btn = new Button(getApplicationContext());
                            params.setMargins(10, 35, 0, 0);
                            btn.setId(quiz.getId());
                            btn.setText(quiz.getName());
                            btn.setGravity(Gravity.LEFT);
                            btn.setLayoutParams(params);
                            btn.setBackgroundColor(Color.WHITE);
                            btn.setTextColor(Color.BLACK);
                            btn.setTextSize(30);

                           btn.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {

                                   MainActivity.api.getResult(MainActivity.current_user.getId(), quiz.getId(), new Callback<Result>() {
                                       @Override
                                       public void success(Result result, Response response) {

//                                          Toast.makeText(getApplicationContext(), result.getResult(), Toast.LENGTH_SHORT).show();
                                           final AlertDialog alertDialog = new AlertDialog.Builder(studentresult.this).create();
                                            try {
                                                alertDialog.setMessage("Your Result Is " + result.getResult());
                                                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        alertDialog.dismiss();
                                                    }
                                                });
                                                alertDialog.show();
                                            }catch (NullPointerException e){
                                                alertDialog.setMessage("Not now");
                                                alertDialog.setButton("OK",new DialogInterface.OnClickListener(){
                                                    public void onClick(DialogInterface dialog,int which){
                                                        alertDialog.dismiss();
                                                    }
                                                });
                                                alertDialog.show();
                                            }
                                       }

                                       @Override
                                       public void failure(RetrofitError retrofitError) {
                                            Toast.makeText(studentresult.this,"Results is not published yet",Toast.LENGTH_LONG).show();
                                       }
                                   });
                               }
                           });

                            A.addView(btn);

                            lm.addView(A);

                        }
                    } else {
                        txt.setText("No Results Available Now");
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    txt.setText(retrofitError.getMessage());
                    txt.setTextColor(Color.RED);
                    Log.e("Error", "retrofit", retrofitError);
                }
            });
        }catch (NullPointerException e){
            txt.setText("No Results Available Now");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_studentresult, menu);
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
