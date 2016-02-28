package org.stuntbum.bowlscore.util;

import org.stuntbum.bowlscore.domain.Average;
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


}
