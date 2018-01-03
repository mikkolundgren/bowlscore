package org.stuntbum.bowlscore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.stuntbum.bowlscore.domain.Bowler;

import java.util.List;

/**
 * Created by mikko on 29/02/16.
 */
public interface BowlerRepository extends CrudRepository<Bowler, String> {

    @Query("select bowler from Bowler bowler order by paidwhen desc")
    public List<Bowler> findAllOrderByPaidwhenDesc();

}
