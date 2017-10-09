package org.lgangloff.nbd.repository;

import org.lgangloff.nbd.domain.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface FileRepository extends JpaRepository<File,Long> {

    @Query("select f from File f "
    		+ "where lower(f.name) like :query "
    		+ "or lower(f.contentType) like :query ")
	Page<File> findAll(@Param("query") String query, Pageable pageable);    

}
