package org.stuntbum.bowlscore.repository;

import org.springframework.data.repository.CrudRepository;
import org.stuntbum.bowlscore.domain.Bowler;

/**
 * Created by mikko on 29/02/16.
 */
public interface BowlerRepository extends CrudRepository<Bowler, String> {

}
