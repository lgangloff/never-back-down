package org.lgangloff.nbd.repository;

import org.lgangloff.nbd.domain.WebSiteConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface WebSiteConfigRepository extends JpaRepository<WebSiteConfig,Long> {

	WebSiteConfig findOneByName(String name);    

}
