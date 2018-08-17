package org.stuntbum.bowlscore.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.stuntbum.bowlscore.domain.Average;
import org.stuntbum.bowlscore.domain.League;
import org.stuntbum.bowlscore.domain.Score;
import org.stuntbum.bowlscore.repository.ScoreRepository;
import org.stuntbum.bowlscore.util.Calculator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mikko on 03/01/16.
 */
@RestController
@RequestMapping(value = "/scores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScoreController {

    @Autowired
    private ScoreRepository repository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<Score> getScoresByName(@PathVariable String name) {
        List<Score> scores = repository.findByNameOrderByDateDesc(name);
        //System.out.println("Scores: " + scores.toString());
        return scores;
    }

    @RequestMapping(value = "/stats/{name}", method = RequestMethod.GET)
    public List<Score> getStatsByName(@PathVariable String name) {
        return repository.findStatsByName(name);
    }

    @RequestMapping(value = "/{name}/avg", method = RequestMethod.GET)
    public List<Average> getScoreAvgByName(@PathVariable String name) {
        List<Score> scores = repository.findByNameOrderByDateDesc(name);
        return Calculator.getAvegares(scores);
    }

    @RequestMapping(value = "/{name}/{score}", method = RequestMethod.POST)
    public Score addScore(@PathVariable String name, @PathVariable int score) {

        if (score < 0 || score > 300) {
            return new Score("Invalid value " + score, -1, new Date());
        }

        return repository.save(new Score(name, score, new Date()));
    }

    @RequestMapping(value = "/{name}/serie", method = RequestMethod.POST)
    public Score addSerie(@PathVariable String name, @RequestBody Score score) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        repository.delete(new Score(id));
        return "{\"id\": " + id + "}";
    }

    @RequestMapping(value = "/league", method = RequestMethod.GET)
    public League getLeague(@RequestParam boolean fullteam, @RequestParam String year) throws Exception {
        if (StringUtils.isEmpty(year)) {
            year = "2018";
        }
        String start = year + "-08-01";
        String end = "" + Integer.parseInt(year) + 1 + "-07-31";
        List<Score> scores = repository.findAllByOrderByTimestampAsc(sdf.parse(start), sdf.parse(end));
        //List<Score> scores = repository.findAll();
        //System.out.println("Scores: " + scores.toString());
        return Calculator.generateLeague(scores, fullteam);
    }

}
