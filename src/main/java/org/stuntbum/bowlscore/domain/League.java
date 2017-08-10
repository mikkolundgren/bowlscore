package org.stuntbum.bowlscore.domain;

import java.util.List;

public class League {

    private List<LeagueScore> scores;

    public List<LeagueScore> getScores() {
        return scores;
    }

    public void setScores(List<LeagueScore> scores) {
        this.scores = scores;
    }
}
