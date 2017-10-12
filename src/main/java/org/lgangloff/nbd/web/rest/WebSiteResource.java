package org.lgangloff.nbd.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.lgangloff.nbd.domain.File;
import org.lgangloff.nbd.domain.WebSiteConfig;
import org.lgangloff.nbd.domain.i18n.I18nKey;
import org.lgangloff.nbd.domain.i18n.I18nValue;
import org.lgangloff.nbd.service.I18nService;
import org.lgangloff.nbd.service.StorageService;
import org.lgangloff.nbd.service.WebSiteService;
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
		return Optional.ofNullable(webSiteService.getWebSiteConfig()).map(web -> new ResponseEntity<>(web, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * GET /website/i18n -> get the website i18n properties config.
	 */
	@RequestMapping(value = "/website/i18n", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<I18nKey, List<I18nValue>>> getWebSiteI18n() {
		return Optional.ofNullable(i18nService.findWebSiteI18nValues()).map(web -> new ResponseEntity<>(web, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
