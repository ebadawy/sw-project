package com.example.khaled.takequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rest.model.Group;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;


public class GroupList extends ActionBarActivity implements View.OnClickListener{
Button deletegroup;
    Button addgroup;
    LinearLayout maingrouplayout;
    EditText groupname;
    int counter =0;
    List<Group> groups = new ArrayList<>();
    List<EditText> groupsname = new ArrayList<>();
    List<LinearLayout> layouts = new ArrayList<>();
    List<TextView> Groupsstudentno = new ArrayList<>();
    Group group;
    int x =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        addgroup =(Button)findViewById(R.id.addgroup);
        deletegroup =(Button)findViewById(R.id.deletegroup);
        maingrouplayout =(LinearLayout)findViewById(R.id.maingrouplayout);
        addgroup.setOnClickListener(this);
        deletegroup.setOnClickListener(this);
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

        ;
        groupname = new EditText(GroupList.this);
        final AlertDialog.Builder alertDialog =new AlertDialog.Builder(this);
        final EditText input = new EditText(GroupList.this);
        input.setHint("Group Name");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        final LinearLayout.LayoutParams ls = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("create",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                if (input.getText().toString().equals("")) {
                    Toast.makeText(GroupList.this, "Please write a group name", Toast.LENGTH_SHORT).show();
                } else {
                    TextView sno = new TextView(GroupList.this);
                    sno.setLayoutParams(ls);
                    sno.setText("0");
                    LinearLayout l = new LinearLayout(GroupList.this);
                    l.setLayoutParams(ls);
                    sno.setGravity(Gravity.RIGHT);
                    groupname.setTextSize(20);
                    sno.setTextSize(20);
                    l.setOrientation(LinearLayout.HORIZONTAL);
                    groupname.setId(counter);
                    counter++;
                    groupname.setText(input.getText().toString());
                    groupsname.add(groupname);
                    layouts.add(l);
                    Groupsstudentno.add(sno);
                    l.addView(groupname);
                    l.addView(sno);
                    maingrouplayout.addView(l);

                }
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog alert=alertDialog.create();
        alertDialog.show();
        MainActivity.api.createGroup(group,MainActivity.current_user.getId(),new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {

            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }
    public void deletegroupClick(){
        if(groupsname.size()>0) {

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            final EditText input = new EditText(GroupList.this);
            input.setHint("Deleted Group Name");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams ls = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input);
            alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    boolean found = false;
                    String deletedGroup = input.getText().toString();
                    for (int i = 0; i < groupsname.size(); i++)
                        if (deletedGroup.equals(groupsname.get(i).getText().toString())) {
                            maingrouplayout.removeView(layouts.get(i));
                            groupsname.remove(i);
                            layouts.remove(i);
                            Groupsstudentno.remove(i);

                            found = true;
                        }

                    if(!found)
                        Toast.makeText(GroupList.this, "Group Doesn't exist", Toast.LENGTH_SHORT).show();

                }


            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            AlertDialog alert = alertDialog.create();
            alertDialog.show();
        }
        else{
            Toast.makeText(GroupList.this,"No groups to delete",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addgroup:
                addGroupClick();
                break;
            case R.id.deletegroup:
                deletegroupClick();
                break;
        }

    }
}
