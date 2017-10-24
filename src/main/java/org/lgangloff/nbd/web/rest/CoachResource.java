package org.lgangloff.nbd.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.lgangloff.nbd.domain.Coach;
import org.lgangloff.nbd.service.CoachService;
import org.lgangloff.nbd.web.rest.util.HeaderUtil;
import org.lgangloff.nbd.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
public class CoachResource {

	private final Logger log = LoggerFactory.getLogger(CoachResource.class);
	
	@Autowired
	private CoachService coachService;
    
    
	/**
	 * POST /coachs -> Create a new coach.
	 */
	@RequestMapping(value = "/coachs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Coach> create(@Valid @RequestBody Coach coach) throws URISyntaxException {
		log.debug("REST request to save Coach : {}", coach);
		if (coach.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new coach cannot already have an ID").body(null);
		}
		
		Coach result = coachService.saveOrUpdate(coach);
		
		return ResponseEntity.created(new URI("/api/coach/" + result.getId()))
				.headers(HeaderUtil.createEntityUpdateAlert("coach", result.getId().toString()))
				.body(result);
	}

	
	/**
	 * PUT /coachs -> Updates an existing coach.
	 */

	@RequestMapping(value = "/coachs", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Coach> update(@Valid @RequestBody Coach coach) throws URISyntaxException {
		log.debug("REST request to update Coach : {}", coach);
		if (coach.getId() == null) {
			return create(coach);
		}
		Coach result = coachService.saveOrUpdate(coach);
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert("coach", coach.getId().toString()))
				.body(result);
	}
		
	/**
	 * GET /coachs -> get all the coachs.
	 */
	@RequestMapping(value = "/coachs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Coach>> getAll(
			@RequestParam(value = "page", required = false) Integer offset,
			@RequestParam(value = "per_page", required = false) Integer limit,
			@RequestParam(value = "query", required = false) String query) throws URISyntaxException {
		Pageable generatePageRequest = PaginationUtil.generatePageRequest(offset, limit);
		
		List<Coach> page = doFindAll(generatePageRequest, query);
		//HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/coachs", offset, limit);
		
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	
	protected List<Coach> doFindAll(Pageable generatePageRequest, String query) {
		query = query == null ? "%" : "%" + query.replaceAll("\\*", "%").toLowerCase() + "%";
		return coachService.findAll(query);
	}

	/**
	 * GET /coachs/:id -> get the "id" coach.
	 */
	@RequestMapping(value = "/coachs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Coach> get(@PathVariable Long id) {
		log.debug("REST request to get Coach : {}", id);
		return coachService.findOne(id)
				.map(coach -> new ResponseEntity<>(coach, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	

	/**
	 * DELETE /coachs/:id -> delete the "id" coach.
	 */

	@RequestMapping(value = "/coachs/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.debug("REST request to delete Coach : {}", id);
		coachService.deleteUser(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("coach", id.toString())).build();
	}
	
}
