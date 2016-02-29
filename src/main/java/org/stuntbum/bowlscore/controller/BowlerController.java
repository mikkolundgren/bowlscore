package org.stuntbum.bowlscore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stuntbum.bowlscore.domain.Bowler;
import org.stuntbum.bowlscore.domain.Score;
import org.stuntbum.bowlscore.repository.BowlerRepository;
import org.stuntbum.bowlscore.repository.ScoreRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by mikko on 29/02/16.
 */
@RestController
@RequestMapping( value = "/bowlers", produces = MediaType.APPLICATION_JSON_VALUE)
public class BowlerController {

    @Autowired
    private BowlerRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Bowler> getBowlers() {
        return repository.findAll();
    }

    @RequestMapping(value = "{name}", method = RequestMethod.POST)
    public Bowler setPayer(@PathVariable String name) {
        return repository.save(new Bowler(name, new Date()));
    }
}
