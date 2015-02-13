package com.rest.model;


public class CurrentQuiz {
    private static Quiz instance = null;
    protected CurrentQuiz(){};
    public static Quiz getInstance()
    {
        if(instance==null)
            instance=new Quiz();
        return instance;
    }
    public static void setInstance(Quiz q)
    {
        instance = q;
    }
}