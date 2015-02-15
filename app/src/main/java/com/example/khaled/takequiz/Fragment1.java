package com.example.khaled.takequiz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 2/2/2015.
 */
public class Fragment1 extends Fragment implements View.OnClickListener{

    private LinearLayout mLayout;
    EditText rightAnswer;
    private RadioGroup radioGroup;
    counter counterz = new counter();
    int counter = 2;
    EditText question;
    Button addChoice;
    EditText choice;
    RadioButton radioButton;
    public List<EditText> allEds = new ArrayList<EditText>();

    Button check;
    View beko;





    public View onCreateView(LayoutInflater inflater,ViewGroup container,
     Bundle savedInstanceState){

         if(allEds.size()== 0) {
             beko = (ScrollView) inflater.inflate(R.layout.fragment1_layout,
                     container, false);

             mLayout = (LinearLayout) beko.findViewById(R.id.linear1);
            rightAnswer =(EditText)beko.findViewById(R.id.rightChoice);
             radioGroup = (RadioGroup) beko.findViewById(R.id.radioGroup);
             question = (EditText) beko.findViewById(R.id.question);
             choice = (EditText) beko.findViewById(R.id.choice);
             addChoice =(Button)beko.findViewById(R.id.addChoice);
             allEds.add(choice);


             addChoice.setOnClickListener(this);




             return beko;
         }
        else {


             ((ViewGroup)beko.getParent()).removeAllViews();
             return beko;

         }



           }


    public void addChoiceclick(){

        EditText editText = new EditText(getActivity() );
        TextView choiceNumber = new TextView(getActivity());
        choiceNumber.setText(counter+".");
        counter ++;

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(p);

        editText.setHint("Write Choice Here");
        editText.setId(counterz.getcounter());
        LinearLayout A = new LinearLayout(getActivity());
        A.setOrientation(LinearLayout.HORIZONTAL);
        allEds.add(editText);
        Log.d("View", "Start");

        try{
            A.addView(choiceNumber);
            A.addView(editText);
            radioGroup.addView(A);



        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
     public void onClick(View view) {
                switch(view.getId()){
                    case R.id.addChoice:
                        addChoiceclick();
                        break;
                }

            }
     public  List<String> getChoiceList() {
        List<String> choices = new ArrayList<String>();
         for(int i = 0;i<allEds.size();i++) {
             String choice = ((allEds.get(i).getText().toString()));
             choices.add(choice);

         }
         return choices;
     }
    public String getQuestion(){
        String question_ = question.getText().toString();
        return question_;
    }

   public String getRightChoice(){
           int i = Integer.parseInt(rightAnswer.getText().toString()) - 1;
       String rightchoice = allEds.get(i).getText().toString();
       return rightchoice;
   }



}







