package org.lgangloff.nbd.repository;

import org.lgangloff.nbd.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Program entity.
 */
@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {

}
