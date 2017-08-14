package org.stuntbum.bowlscore.util;

import org.stuntbum.bowlscore.domain.Average;
import org.stuntbum.bowlscore.domain.League;
import org.stuntbum.bowlscore.domain.LeagueScore;
import org.stuntbum.bowlscore.domain.Score;

import java.util.*;

/**
 * Created by mikko on 16/01/16.
 */
public class Calculator {

    public static ArrayList<Score> getAvgFromScores(List<Score> scores) {

        //List<Average> averages = new ArrayList<Average>();
        Map<Date, Average> tmp = new TreeMap<Date, Average>();

        for (Score s : scores) {
            if (tmp.containsKey(s.getDate())) {
                Average avg = tmp.get(s.getDate());
                avg.addTotal(s.getScore(), s.getFormattedDate());
            } else {
                Average newAvg = new Average();
                newAvg.addTotal(s.getScore(), s.getFormattedDate());
                tmp.put(s.getDate(), newAvg);
            }
        }


        Iterator<Average> iter = tmp.values().iterator();
        ArrayList<Score> tmpscore = new ArrayList<Score>();
/*
        while(iter.hasNext()) {
            Average a = iter.next();
            Score s = new Score(a.getAvg(), a.getDate());
            tmpscore.add(s);
        }
        */
        return tmpscore;
    }

    public static ArrayList<Average> getAvegares(List<Score> scores) {
        Map<Date, Average> tmp = new TreeMap<Date, Average>();

        for (Score s : scores) {
            if (tmp.containsKey(s.getDate())) {
                Average avg = tmp.get(s.getDate());
                avg.addScore(s);
            } else {
                Average newAvg = new Average();
                newAvg.addScore(s);
                tmp.put(s.getDate(), newAvg);
            }

        }

        Iterator<Average> iter = tmp.values().iterator();
        ArrayList<Average> tmpscore = new ArrayList<Average>();
        while(iter.hasNext()) {
            Average a = iter.next();
            tmpscore.add(a);
        }
        return tmpscore;
    }

    protected League generateLeague(List<Score> scores) {

        League l = generateEmptyLeague();

        if (scores == null || scores.isEmpty()) {
            return l;
        }

        String startDate = scores.get(0).getFormattedDate();
        String curDate = scores.get(0).getFormattedDate();
        int size = scores.size();
        int i = 0;
        List<Score> dayScores = new ArrayList<>();
        while(startDate.equals(curDate) && i <= size) {
            dayScores.add(scores.get(i));
            i++;
            curDate = scores.get(i).getFormattedDate();
        }
        calculateLeagueDay(dayScores, l);
        return l;
    }

    protected static void calculateLeagueDay(List<Score> dayScores, League league) {

        LeagueScore aku = league.getSingleScore("Aku");
        LeagueScore mikko = league.getSingleScore("Mikko");
        LeagueScore olli = league.getSingleScore("Olli");
        int akuCur, mikkoCur, olliCur;
        akuCur = mikkoCur = olliCur = 0;

        int akuBest, mikkoBest, olliBest;
        akuBest = mikkoBest = olliBest = 0;

        int akuTotal, mikkoTotal, olliTotal;
        akuTotal = mikkoTotal = olliTotal = 0;

        for (int i = 0; i < dayScores.size(); i++) {
            Score ds = dayScores.get(i);
            if (ds.getName().equals("Aku")) {
                akuTotal += ds.getScore();
                akuCur = ds.getScore();
                if (ds.getScore() > akuBest) {
                    akuBest = ds.getScore();
                }
            }
            if (ds.getName().equals("Mikko")) {
                mikkoTotal += ds.getScore();
                mikkoCur = ds.getScore();
                if (ds.getScore() > mikkoBest) {
                    mikkoBest = ds.getScore();
                }
            }
            if (ds.getName().equals("Olli")) {
                olliTotal = ds.getScore();
                olliCur += ds.getScore();
                if (ds.getScore() > olliBest) {
                    olliBest = ds.getScore();
                }
            }
            if (akuCur > 0 && mikkoCur > 0 && olliCur > 0) {
                if (akuCur >= mikkoCur && akuCur >= olliCur) {
                    aku.addRoundWin();
                }
                if (mikkoCur >= akuCur && mikkoCur >= olliCur) {
                    mikko.addRoundWin();
                }
                if (olliCur >= akuCur && olliCur >= mikkoCur) {
                    olli.addRoundWin();
                }
                akuCur = mikkoCur = olliCur = 0;
            }
        }
        if (akuTotal >= mikkoTotal && akuTotal >= olliTotal) {
            aku.addBestTotal();
        }
        if (mikkoTotal >= akuTotal && mikkoTotal >= olliTotal) {
            mikko.addBestTotal();
        }
        if (olliTotal >= akuTotal && olliTotal >= mikkoTotal) {
            olli.addBestTotal();
        }

        if (akuBest >= mikkoBest && akuBest >= olliBest) {
            aku.addBestScore();
        }
        if (mikkoBest >= akuBest && mikkoBest >= olliBest) {
            mikko.addBestScore();
        }
        if (olliBest >= akuBest && olliBest >= mikkoBest) {
            olli.addBestScore();
        }

    }

    public static League generateEmptyLeague() {
        League l = new League();
        LeagueScore aku = new LeagueScore("Aku");
        LeagueScore mikko = new LeagueScore("Mikko");
        LeagueScore olli = new LeagueScore("Olli");

        l.addScore(aku);
        l.addScore(mikko);
        l.addScore(olli);
        return l;
    }
}
