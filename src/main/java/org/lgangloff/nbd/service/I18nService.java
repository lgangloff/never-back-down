package org.lgangloff.nbd.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.lgangloff.nbd.config.Constants;
import org.lgangloff.nbd.domain.i18n.I18nValue;
import org.lgangloff.nbd.domain.i18n.enumeration.LangKey;
import org.lgangloff.nbd.repository.I18nRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class I18nService {

	private final Logger log = LoggerFactory.getLogger(I18nService.class);

	@Autowired
	private I18nRepository i18nRepository;
	
	
	public Map<String, String> findWebSiteI18nValues(LangKey lang){
		return i18nRepository.findAllByGroupName(Constants.WEBSITE_CONFIG_NAME, lang).stream().collect(Collectors.toMap(v->v.getI18nKey().getName(), I18nValue::getValue));
	}


}
