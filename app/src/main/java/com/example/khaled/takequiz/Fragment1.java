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
    private Button mButton;
    private RadioGroup radioGroup;
    counter counterz = new counter();
    EditText question;

    EditText choice;
    RadioButton radioButton;
    public List<EditText> allEds = new ArrayList<EditText>();
    List<RadioButton> allRbs = new ArrayList<RadioButton>();
    Button check;
    View beko;





    public View onCreateView(LayoutInflater inflater,ViewGroup container,
     Bundle savedInstanceState){
         if(allRbs.size()== 0) {
             beko = (ScrollView) inflater.inflate(R.layout.fragment1_layout,
                     container, false);

             mLayout = (LinearLayout) beko.findViewById(R.id.linear1);
             mButton = (Button) beko.findViewById(R.id.addChoice);
             radioGroup = (RadioGroup) beko.findViewById(R.id.radioGroup);
             question = (EditText) beko.findViewById(R.id.question);
             choice = (EditText) beko.findViewById(R.id.choice);
             radioButton = (RadioButton) beko.findViewById(R.id.radioButton);
             allEds.add(choice);
             allRbs.add(radioButton);
             check = (Button) beko.findViewById(R.id.check);
             mButton.setOnClickListener(this);
             check.setOnClickListener(this);
             return beko;
         }
        else {

             ((ViewGroup)beko.getParent()).removeAllViews();
             return beko;

         }


           }


    public void addChoiceclick(){

        EditText editText = new EditText(getActivity() );
        RadioButton radiobutton1 = new RadioButton(getActivity());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        radiobutton1.setLayoutParams(p);
        editText.setLayoutParams(p);
        editText.setText("Write Choice Here");
        editText.setId(counterz.getcounter());

        radiobutton1.setId(counterz.getcounter());

        allEds.add(editText);
        allRbs.add(radiobutton1);
        Log.d("View", "Start");

        try{
            radioGroup.addView(radiobutton1);
            radioGroup.addView(editText);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void checkClick(){
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getActivity(), "Choose Right Answer", Toast.LENGTH_SHORT).show();
        }



        else
        {
            Toast.makeText(getActivity(), "Check Passed", Toast.LENGTH_SHORT).show();
        }

System.out.println("no of radiobuttons "+allRbs.size());
    }
    @Override
     public void onClick(View view) {
                switch(view.getId()){
                    case R.id.addChoice:
                        addChoiceclick();
                        break;
                    case R.id.check:
                        checkClick();
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

   public int getRightChoice(){
       int radiocounter = 1;
       for(int i = 0 ;i<allRbs.size();i++) {
          System.out.println("AllRbs size is "+(allRbs.size()));
           if (allRbs.get(i).isChecked()){break;}
           else radiocounter ++;
       }
       return radiocounter;
   }

}







