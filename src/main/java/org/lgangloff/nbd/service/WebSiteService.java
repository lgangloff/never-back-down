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

	@Autowired
	private StorageService storageService;
	@Autowired
	private I18nService i18nService;

	public WebSiteConfig getWebSiteConfig() {
		return webSiteConfigRepository.findOneByName(Constants.WEBSITE_CONFIG_NAME);
	}

	public void update(WebSiteConfig config) {
		config.setName(Constants.WEBSITE_CONFIG_NAME);
		
		storageService.markAsUsed(config.getBackgroundImageFile());
		storageService.markAsUsed(config.getLogo500ImageFile());
		storageService.markAsUsed(config.getLogo300ImageFile());
		
		i18nService.saveI18nValues(config.getI18nFields(), config.getName());
		
		webSiteConfigRepository.save(config);
	}
	
}
