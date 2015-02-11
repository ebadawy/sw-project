package com.example.khaled.takequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rest.model.Quiz;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class studentresult extends ActionBarActivity {

    QuizAPI api;
    Quiz quiz;
    public void pop(View view){
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage("Your Result Is 10");
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
        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearmain);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final TextView result = (TextView) findViewById(R.id.result);
        try {
            api.getQuizzes(new Callback<List<Quiz>>() {
                @Override
                public void success(List<Quiz> quizs, Response response) {
                    // txt.setText(quiz.getName());

                    if(quizs.size()!=0){
                        for (int i=0;i<quizs.size();i++) {
                            //Create Layout
                            LinearLayout l1 = new LinearLayout(getApplicationContext());
                            l1.setOrientation(LinearLayout.HORIZONTAL);
                            //Create Button
                            final Button btn = new Button(getApplicationContext());
                            btn.setId(quiz.getId());
                            btn.setText(quiz.getName());
                            btn.setGravity(Gravity.CENTER);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    final AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                                    //need to get result from server
                                    alertDialog.setMessage("Your Result Is ");
                                    alertDialog.setButton("OK",new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialog,int which){
                                            alertDialog.dismiss();
                                        }
                                    });
                                    alertDialog.show();
                                }
                            });

                        }
                    }else{
                        result.setText("No Results Available Now");
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    result.setText("Cannot Connect To Server");
                    result.setTextColor(Color.RED);
                }
            });
        }catch (NullPointerException e){
            result.setText("No Results Available Now");
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
