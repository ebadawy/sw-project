package com.rest.model;

import com.savagelook.android.Lazy;

import java.util.List;

public class Group {
    private String group_name;
    private int students_number;
    private List<User> users;

    public Group() {
        this.group_name = null;
        this.students_number = 0;
        this.users = null;
    }

    public String getGroupName() {
        return group_name;
    }

    public int getStudentsNumber() {
        return students_number;
    }

    public List<User> getUsers() {
        return users;
    }

}
