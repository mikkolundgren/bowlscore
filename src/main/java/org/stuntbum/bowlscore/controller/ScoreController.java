package org.stuntbum.bowlscore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stuntbum.bowlscore.domain.Score;
import org.stuntbum.bowlscore.repository.ScoreRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by mikko on 03/01/16.
 */
@RestController
@RequestMapping( value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScoreController {

    @Autowired
    private ScoreRepository repository;

    @RequestMapping(value = "/scores/{name}", method = RequestMethod.GET)
    public List<Score> getScoresByName(@PathVariable String name) {

        return repository.findByName(name);

    }

    @RequestMapping(value = "/scores/{name}/{score}", method = RequestMethod.POST)
    public Score addScore(@PathVariable String name, @PathVariable int score) {
        return repository.save(new Score(name, score, new Date()));
    }

    @RequestMapping(value = "/scores/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        repository.delete(new Score(id));
        return "{\"id\": " + id + "}";
    }
}
