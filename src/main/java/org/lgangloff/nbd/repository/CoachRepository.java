package org.lgangloff.nbd.repository;

import java.util.List;

import org.lgangloff.nbd.domain.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface CoachRepository extends JpaRepository<Coach,Long> {

	@Query("select c from Coach c "
			+ "left join fetch c.photo ")
	List<Coach> findAll();    

}
