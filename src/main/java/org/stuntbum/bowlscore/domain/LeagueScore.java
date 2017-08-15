package org.stuntbum.bowlscore.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LeagueScore {

    private int roundWin = 0;
    private int bestScore = 0;
    private int bestTotal = 0;

    private String bowler;

    public LeagueScore(String bowler) {
        this.bowler = bowler;
    }

    public void addRoundWin() {
        this.roundWin++;
    }

    public void addBestScore() {
        this.bestScore++;
    }

    public void addBestTotal() {
        this.bestTotal++;
    }

    public int getRoundWin() {
        return roundWin;
    }

    public int getBestScore() {
        return bestScore;
    }

    public int getBestTotal() {
        return bestTotal;
    }

    public String getBowler() {
        return bowler;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("bowler: ", bowler)
                .append("roundWin: ", roundWin)
                .append("bestScore: ", bestScore)
                .append("bestTotal: ", bestTotal)
                .toString();
    }

}
