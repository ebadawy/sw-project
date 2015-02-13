package com.example.khaled.takequiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class QuizMaininfo extends ActionBarActivity implements View.OnClickListener{
    Button nextCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_maininfo);
        nextCreate =(Button)findViewById(R.id.nextCreate);
        nextCreate.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_maininfo, menu);
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
    private void nextCreateClick() {
        EditText subject = (EditText) findViewById(R.id.subject);
        EditText deadline = (EditText) findViewById(R.id.deadline);
        EditText timelimit = (EditText) findViewById(R.id.timelimit);
        EditText quizmark = (EditText) findViewById(R.id.quizmark);
        TextView subjectWarning = (TextView) findViewById(R.id.subjectWarning);
        TextView deadlineWarning = (TextView) findViewById(R.id.deadlineWarning);
        TextView timelimitWarning = (TextView) findViewById(R.id.timelimitWarning);
        TextView quizmarkWarning = (TextView) findViewById(R.id.quizmarkWarning);
        String subject_ = subject.getText().toString();
        String deadline_ = deadline.getText().toString();
        String timelimit_ = timelimit.getText().toString();
        String quizmark_ = quizmark.getText().toString();
        if (subject_.equals("") || subject_.equals("Subject")) {
            subjectWarning.setText("Enter Subject");
            subjectWarning.setTextColor(Color.RED);
        }
        if (deadline_.equals("") || deadline_.equals("Deadline")) {
            deadlineWarning.setText("Enter Deadline");
            deadlineWarning.setTextColor(Color.RED);
        }
        if (timelimit_.equals("") || timelimit_.equals("Time Limit")) {
            timelimitWarning.setText("Enter Time Limit");
            timelimitWarning.setTextColor(Color.RED);
        }
        if (quizmark_.equals("") || quizmark_.equals("Quiz Mark")) {
            quizmarkWarning.setText("Enter Quiz Mark");
            quizmarkWarning.setTextColor(Color.RED);
        } else {
            try {
                int i = Integer.parseInt(quizmark_);
                Intent intent = new Intent(QuizMaininfo.this, QuizQuestionAnswerDoctor.class);
                Bundle extras = new Bundle();
                extras.putString("subject", subject_);
                extras.putString("deadline", deadline_);
                extras.putString("timelimit", timelimit_);
                extras.putString("quizmark", quizmark_);
                intent.putExtras(extras);


                startActivity(intent);
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Enter a number in QuizMark", Toast.LENGTH_SHORT).show();

            }


        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.nextCreate:
                nextCreateClick();
                break;
        }

    }
}
