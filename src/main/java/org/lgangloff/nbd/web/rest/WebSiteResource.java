package org.lgangloff.nbd.web.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.lgangloff.nbd.domain.WebSiteConfig;
import org.lgangloff.nbd.service.I18nService;
import org.lgangloff.nbd.service.WebSiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing User.
 */
@RestController
@RequestMapping("/api")
public class WebSiteResource {

	private final Logger log = LoggerFactory.getLogger(WebSiteResource.class);

	@Autowired
	private WebSiteService webSiteService;
	@Autowired
	private I18nService  i18nService;

	/**
	 * GET /website -> get the website config.
	 */
	@RequestMapping(value = "/website", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WebSiteConfig> getWebSite() {
		return Optional.ofNullable(webSiteService.getWebSiteConfig()).map(web -> {
				web.setI18nFields(i18nService.findI18nValues(web.getName()));
				return new ResponseEntity<>(web, HttpStatus.OK);
			})
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	

	@RequestMapping(value = "/website", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WebSiteConfig> updateWebSite(@Valid @RequestBody WebSiteConfig config) {
		log.info("PUT request to save config: {}", config);
		this.webSiteService.update(config);

		return getWebSite();
	}
}
