package org.lgangloff.nbd.repository;

import org.lgangloff.nbd.domain.WebSiteConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface WebSiteConfigRepository extends JpaRepository<WebSiteConfig,Long> {

	@Query("select config from WebSiteConfig config "
			+ "left join fetch config.backgroundImageFile "
			+ "left join fetch config.logo500ImageFile "
			+ "left join fetch config.logo300ImageFile "
			+ "where config.name = :name")
	WebSiteConfig findOneByName(@Param("name") String name);    

}
