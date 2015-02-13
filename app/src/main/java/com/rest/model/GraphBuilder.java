package com.rest.model;

import com.jjoe64.graphview.series.DataPoint;

import java.util.List;

public class GraphBuilder {

    private List<Quiz> quizzes;
    private List<Result> results;

    public DataPoint[] generateData() {

        int count = quizzes.size();
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            int x = quizzes.get(i).getId();

            int  y = results.get(i).getResult();
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }
}
