package org.stuntbum.bowlscore.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.stuntbum.bowlscore.domain.Score;

import java.util.Date;
import java.util.List;

/**
 * Created by mikko on 30/12/15.
 */
public interface ScoreRepository extends CrudRepository<Score, String> {

    @Cacheable(value = "scoresByName")
    List<Score> findByNameOrderByDateDesc(@Param("name") String name);

    @Cacheable(value = "statsByName")
    @Query("select max(score), min(score), avg(score), count(score) from Score where name = :name")
    List<Score> findStatsByName(@Param("name") String name);

    @Override
    @CacheEvict(value = {"scoresByName", "statsByName", "scoresOrderTimestamp"}, allEntries = true)
    <S extends Score>S save(S score);

    @Override
    @CacheEvict(value = {"scoresByName", "statsByName"}, allEntries = true)
    void delete(Score score);

    @Cacheable(value = "scoresOrderTimestamp")
    @Query("select foo from Score foo where date > :start and date < :end order by timestamp asc")
    List<Score> findAllByOrderByTimestampAsc(@Param("start") Date start, @Param("end") Date end);

}
