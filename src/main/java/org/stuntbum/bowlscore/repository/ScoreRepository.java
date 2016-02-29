package org.stuntbum.bowlscore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.stuntbum.bowlscore.domain.Bowler;
import org.stuntbum.bowlscore.domain.Score;

import java.util.List;

/**
 * Created by mikko on 30/12/15.
 */
public interface ScoreRepository extends CrudRepository<Score, String> {

    List<Score> findByName(@Param("name") String name);



}
