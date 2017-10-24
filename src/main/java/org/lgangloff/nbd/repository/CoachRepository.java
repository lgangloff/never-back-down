package org.lgangloff.nbd.repository;

import java.util.List;
import java.util.Optional;

import org.lgangloff.nbd.domain.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Coach entity.
 */
@Repository
public interface CoachRepository extends JpaRepository<Coach,Long> {

    @Query("select c from Coach c "
			+ "left join fetch c.photo "
    		+ "where lower(c.displayName) like :query ")
	List<Coach> findAll(@Param("query") String query);


    @Query("select c from Coach c "
			+ "left join fetch c.photo "
    		+ "where c.id = :id ")
	Optional<Coach> findOne(@Param("id") Long id);
}
