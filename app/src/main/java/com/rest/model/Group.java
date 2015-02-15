package com.rest.model;

import com.savagelook.android.Lazy;

import java.util.List;

public class Group {
    private int id;
    private String group_name;
    private int students_number;
    private List<User> users;

    public Group(String name) {
        this.id = 0;
        this.group_name = name;
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

    public int getId() {
        return this.id;
    }
    public void setGroupName(String groupName){
        this.group_name = groupName;
    }
    public void setStudentNumber(int studentNumber){
        this.students_number = studentNumber;
    }


    public boolean hasStudent(String studentName) {
        for(User user : users)
            if(user.getUserName().equals(studentName))
                return true;
        return false;
    }

}
