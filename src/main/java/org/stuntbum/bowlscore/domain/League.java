package org.stuntbum.bowlscore.domain;

import java.util.ArrayList;
import java.util.List;

public class League {

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

}
