package com.example.khaled.takequiz;


public class User {
    private String user_name;
    private String password;

    public User() {
        this.user_name = null;
        this.password = null;
    }

    public User(String name, String pass) {
        this.user_name = name;
        this.password = pass;
    }

    public String getUserName() {return user_name;}
    public String getPassword() {return password;}

    public void setUserName(String id) {this.user_name = id;}
    public void setPassword(String pass) {this.password = pass;}

}
