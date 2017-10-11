package org.lgangloff.nbd.service;

import org.lgangloff.nbd.config.Constants;
import org.lgangloff.nbd.domain.WebSiteConfig;
import org.lgangloff.nbd.repository.WebSiteConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WebSiteService {

	private final Logger log = LoggerFactory.getLogger(WebSiteService.class);

	@Autowired
	private WebSiteConfigRepository webSiteConfigRepository;

	public WebSiteConfig getWebSiteConfig() {
		return webSiteConfigRepository.findOneByName(Constants.WEBSITE_CONFIG_NAME);
	}
	
}
