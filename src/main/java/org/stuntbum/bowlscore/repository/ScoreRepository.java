package org.stuntbum.bowlscore.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "scoresByName")
    List<Score> findByName(@Param("name") String name);

    @Override
    @CacheEvict(value = "scoresByName", allEntries = true)
    <S extends Score>S save(S score);

    @Override
    @CacheEvict(value = "scoresByName", allEntries = true)
    void delete(Score score);

}
