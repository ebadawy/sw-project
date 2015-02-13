package com.example.khaled.takequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class GroupList extends ActionBarActivity implements View.OnClickListener{
Button deletegroup;
    Button addgroup;
    LinearLayout maingrouplayout;
    EditText groupname;
    int counter =0;
    List<EditText> groupsname = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        addgroup =(Button)findViewById(R.id.addgroup);
        deletegroup =(Button)findViewById(R.id.deletegroup);
        maingrouplayout =(LinearLayout)findViewById(R.id.maingrouplayout);
        addgroup.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_list, menu);
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

    public void addGroupClick(){
        groupname = new EditText(GroupList.this);
        final AlertDialog.Builder alertDialog =new AlertDialog.Builder(this);
        final EditText input = new EditText(GroupList.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("create",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
            groupname.setId(counter);
            counter ++;
            groupname.setText(input.getText().toString());
            groupsname.add(groupname);
            maingrouplayout.addView(groupname);

            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog alert=alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addgroup:
                addGroupClick();
                break;
        }

    }
}
