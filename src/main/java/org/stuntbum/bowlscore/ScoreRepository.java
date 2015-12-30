package org.stuntbum.bowlscore;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by mikko on 30/12/15.
 */
public interface ScoreRepository extends MongoRepository<Score, String> {

    List<Score> findByName(@Param("name") String name);



}
