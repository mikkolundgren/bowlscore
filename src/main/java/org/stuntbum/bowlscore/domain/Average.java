package org.stuntbum.bowlscore.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mikko on 16/01/16.
 */
public class Average {

    private int total = 0;
    private int count = 0;
    private int avg = 0;
    private String date;
    private List<Score> scores = new ArrayList<Score>();

    public void addScore(Score score) {
        this.scores.add(score);
    }

    public void addTotal(int score, String date) {
        this.total += score;
        this.count++;
        this.avg = total / count;
        this.date = date;
    }

    public int getAvg() {
        int total = 0;
        for (Score s : scores) {
            total += s.getScore();
        }
        int size = scores.size() == 0 ? 1 : scores.size();
        return total / size;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public String getDate() {
        return scores.get(0).getFormattedDate();
    }

    public int getCount() {return this.scores.size() + 1; }

}

