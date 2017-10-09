package org.lgangloff.nbd.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.lgangloff.nbd.domain.File;
import org.lgangloff.nbd.service.StorageService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * REST controller for managing User.
 */
@RestController
@RequestMapping("/api")
public class FileResource {

	private final Logger log = LoggerFactory.getLogger(FileResource.class);

	@Autowired
	private StorageService storageService;

	/**
	 * POST /files -> Create a new file.
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(value = "/files", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<File> create(@RequestParam("file") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes) throws URISyntaxException, IOException {

		log.debug("REST request to save a file : {}", multipartFile);

		File file = storageService.store(multipartFile);

		return ResponseEntity.created(new URI("/api/file/" + file.getId()))
				.headers(HeaderUtil.createEntityUpdateAlert("file", file.getId().toString())).body(file);
	}

	/**
	 * GET /files/:uuid -> download the "uuid" file.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/files/{uuid}", method = RequestMethod.GET)
	public void download(@PathVariable String uuid, @RequestParam(value = "dl", required = false) boolean forceDownload, HttpServletResponse response) throws IOException {
		log.debug("REST request to download File : {}", uuid);

		File file = storageService.findOneForDownload(uuid);

		response.setContentLengthLong(file.getSize());
		response.setContentType(file.getContentType());
		
		if(forceDownload)
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

		response.getOutputStream().write(file.getDatas());
		response.flushBuffer();

	}

	/**
	 * GET /files -> get all the files.
	 */
	@RequestMapping(value = "/files", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<File>> getAll(@RequestParam(value = "page", required = false) Integer offset,
			@RequestParam(value = "per_page", required = false) Integer limit,
			@RequestParam(value = "query", required = false) String query) throws URISyntaxException {

		Pageable generatePageRequest = PaginationUtil.generatePageRequest(offset, limit);

		Page<File> page = doFindAll(generatePageRequest, query);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/files", offset, limit);
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	protected Page<File> doFindAll(Pageable generatePageRequest, String query) {
		query = query == null ? "%" : "%" + query.replaceAll("\\*", "%").toLowerCase() + "%";
		return storageService.findAll(query, generatePageRequest);
	}

	/**
	 * GET /files/:id -> get the "id" file.
	 */
	@RequestMapping(value = "/files/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<File> get(@PathVariable Long id) {
		log.debug("REST request to get File : {}", id);
		return Optional.ofNullable(storageService.findOne(id)).map(file -> new ResponseEntity<>(file, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /files/:id -> delete the "id" file.
	 */

	@RequestMapping(value = "/files/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.debug("REST request to delete File : {}", id);
		storageService.deleteFile(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("file", id.toString())).build();
	}

}
