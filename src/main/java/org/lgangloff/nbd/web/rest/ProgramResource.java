package org.lgangloff.nbd.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.lgangloff.nbd.domain.Program;
import org.lgangloff.nbd.exception.FieldRequiredException;
import org.lgangloff.nbd.service.ProgramService;
import org.lgangloff.nbd.web.rest.util.HeaderUtil;
import org.lgangloff.nbd.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing User.
 */
@RestController
@RequestMapping("/api")
public class ProgramResource {

	private final Logger log = LoggerFactory.getLogger(ProgramResource.class);
	
	@Autowired
	private ProgramService programservice;
    
    
	/**
	 * POST /programs -> Create a new program.
	 * @throws FieldRequiredException 
	 */
	@RequestMapping(value = "/programs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Program> create(@Valid @RequestBody Program program) throws URISyntaxException, FieldRequiredException {
		log.debug("REST request to save Program : {}", program);
		if (program.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new program cannot already have an ID").body(null);
		}
		
		Program result = programservice.saveOrUpdate(program);
		
		return ResponseEntity.created(new URI("/api/program/" + result.getId()))
				.headers(HeaderUtil.createEntityUpdateAlert("program", result.getId().toString()))
				.body(result);
	}

	
	/**
	 * PUT /programs -> Updates an existing program.
	 * @throws FieldRequiredException 
	 */

	@RequestMapping(value = "/programs", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Program> update(@Valid @RequestBody Program program) throws URISyntaxException, FieldRequiredException {
		log.debug("REST request to update Program : {}", program);
		if (program.getId() == null) {
			return create(program);
		}
		Program result = programservice.saveOrUpdate(program);
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert("program", program.getId().toString()))
				.body(result);
	}
		
	/**
	 * GET /programs -> get all the programs.
	 */
	@RequestMapping(value = "/programs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Program>> getAll(
			@RequestParam(value = "page", required = false) Integer offset,
			@RequestParam(value = "per_page", required = false) Integer limit,
			@RequestParam(value = "query", required = false) String query) throws URISyntaxException {
		Pageable generatePageRequest = PaginationUtil.generatePageRequest(offset, limit);
		
		List<Program> page = doFindAll(generatePageRequest, query);
		//HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/programs", offset, limit);
		
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	
	protected List<Program> doFindAll(Pageable generatePageRequest, String query) {
		query = query == null ? "%" : "%" + query.replaceAll("\\*", "%").toLowerCase() + "%";
		return programservice.findAll(query);
	}

	/**
	 * GET /programs/:id -> get the "id" program.
	 */
	@RequestMapping(value = "/programs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Program> get(@PathVariable Long id) {
		log.debug("REST request to get Program : {}", id);
		return programservice.findOne(id)
				.map(program -> new ResponseEntity<>(program, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	

	/**
	 * DELETE /programs/:id -> delete the "id" program.
	 */

	@RequestMapping(value = "/programs/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.debug("REST request to delete Program : {}", id);
		programservice.deleteProgram(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("program", id.toString())).build();
	}
	
}
