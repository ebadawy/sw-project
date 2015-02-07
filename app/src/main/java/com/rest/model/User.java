package com.rest.model;


import com.example.khaled.takequiz.MainActivity;
import com.example.khaled.takequiz.QuizAPI;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class User {
    private int id;
    private String user_name;
    private String password;
    private int user_result = 0;
    private  String role;
    private List<Group> groups;

    public User() {
        this.user_name = null;
        this.password = null;
        this.groups = null;
        this.id = 0;
    }

    public User(String name, String pass, String r) {
        this.user_name = name;
        this.password = pass;
        this.role = r;
        this.groups = null;
        this.id = 0;

    }

    public String getUserName() {return user_name;}
    public String getPassword() {return password;}
    public int getUserResult() {return user_result;}
    public String getRole() {return role;}
    public List<Group> getGroups() {return groups;}
    public int getId(){return this.id;}


    public void setUserName(String id) {this.user_name = id;}
    public void setPassword(String pass) {this.password = pass;}
    public void setUserResult(int rslt) {this.user_result = rslt;}
    public void setRole(String r) {this.role = role;}

}
