package com.example.khaled.takequiz;


public class User {
    private String user_id;
    private String password;

    public User() {
        user_id = null;
        password = null;
    }

    public User(String id, String pass) {
        user_id = id;
        password = pass;
    }

    public String getUserID() {return user_id;}
    public String getPassword() {return password;}

    public void setUserId(String id) {user_id = id;}
    public void setPassword(String pass) {password = pass;}

}
