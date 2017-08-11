package org.stuntbum.bowlscore.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.stuntbum.bowlscore.domain.Average;
import org.stuntbum.bowlscore.domain.League;
import org.stuntbum.bowlscore.domain.Score;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mikko on 16/01/16.
 */
@RunWith(JUnit4.class)
//@SpringApplicationConfiguration(AppConfig.class)
public class CalculatorTest {

    @Test
    public void testGetAverages() {
        ArrayList<Average> avgs = Calculator.getAvegares(getTestData1());
        assertTrue(avgs.size() == 2);
        System.out.println("avgs = " + avgs);
    }

    @Test
    public void testLeagueCalc() {
        List<Score> scores = getTestData1();
        League l = Calculator.generateEmptyLeague();
        assertNotNull(l);
        Calculator.calculateLeagueDay(scores, l);
        assertNotNull(l);
    }

    private List<Score> getTestData1() {
        List<Score> scores = new ArrayList<Score>();
        Score s1 = new Score("Aku", 200, getDateFromString("01.01.2016"));
        scores.add(s1);
        Score s2 = new Score("Aku", 100, getDateFromString("01.01.2016"));
        scores.add(s2);
        Score s3 = new Score("Aku", 300, getDateFromString("07.01.2016"));
        Score s4 = new Score("Aku", 200, getDateFromString("07.01.2016"));
        scores.add(s3);
        scores.add(s4);
        return scores;
    }

    private Date getDateFromString(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date d = null;
        try {
            d = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

}
