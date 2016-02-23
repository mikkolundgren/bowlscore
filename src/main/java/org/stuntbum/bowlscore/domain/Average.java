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
    private Date date;

    public void addTotal(int score, Date date) {
        this.total += score;
        this.count++;
        this.avg = total / count;
        this.date = date;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
