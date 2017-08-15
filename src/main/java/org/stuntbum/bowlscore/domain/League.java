package org.stuntbum.bowlscore.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class League {

    private int numberOfSeries;
    private int numberOfTimes;

    private List<LeagueScore> scores = new ArrayList<>();

    public List<LeagueScore> getScores() {
        return scores;
    }

    public void setScores(List<LeagueScore> scores) {
        this.scores = scores;
    }

    public void addScore(LeagueScore score) {
        this.scores.add(score);
    }

    public LeagueScore getSingleScore(String name) {
        for (LeagueScore ls : scores) {
            if (name.equals(ls.getBowler())) {
                return ls;
            }
        }
        return null;
    }

    public int getNumberOfSeries() {
        return numberOfSeries;
    }

    public void setNumberOfSeries(int numberOfSeries) {
        this.numberOfSeries = numberOfSeries;
    }

    public void addNumberOfSeries() {
        this.numberOfSeries++;
    }

    public int getNumberOfTimes() {
        return numberOfTimes;
    }

    public void setNumberOfTimes(int numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }

    public void addNumberOfTimes() {
        this.numberOfTimes++;
    }
    @Override
    public String toString() {

        return new ToStringBuilder(this)
                .append("scores: ", scores)
                .toString();
    }

}
