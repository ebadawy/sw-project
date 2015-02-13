package com.rest.model;

import com.jjoe64.graphview.series.DataPoint;
import java.util.Arrays;
import java.lang.*;
import java.util.List;

public class GraphBuilder {

    private List<Quiz> quizzes;
    private List<Result> results;

    public DataPoint[] generateData() {
        int max = 0;
        int count = quizzes.size();
        DataPoint[] values = new DataPoint[count];
        for (int i = 0; i < count; i++) {
            int x = quizzes.get(i).getId();
            if (x > max) {
                int y = results.get(i).getResult();
                DataPoint v = new DataPoint(x, y);
                values[i] = v;
                System.out.println(quizzes.get(i).getId() + "   " + results.get(i).getResult());
                max = x;
            } else {
                values[i] = values[i - 1];
                int xs = quizzes.get(i).getId();
                int y = results.get(i).getResult();
                DataPoint v = new DataPoint(x, y);
                values[i - 1] = v;
            }
        }

        return values;
    }

    public String[] quizNamesArranged() {
        int max = 0;
        int count = quizzes.size();
        String[] values = new String[count];
        for (int i = 0; i < count; i++) {
            int x = quizzes.get(i).getId();
            if (x > max) {
                values[i] = quizzes.get(i).getName();
                max = x;
            } else {
                values[i] = values[i - 1];
                String v = quizzes.get(i).getName();
                values[i - 1] = v;
            }
        }
        return values;
    }
}
