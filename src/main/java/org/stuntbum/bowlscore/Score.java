package org.stuntbum.bowlscore;


import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by mikko on 30/12/15.
 */
public class Score {

    @Id
    private String id;

    private String name;
    private int score;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
