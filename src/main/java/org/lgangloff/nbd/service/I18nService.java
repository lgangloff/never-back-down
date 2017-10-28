package org.lgangloff.nbd.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.lgangloff.nbd.domain.i18n.I18nKey;
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
	
	
	public Map<String, String> findI18nValues(String groupName, LangKey lang){
		return i18nRepository.findAllByGroupName(groupName, lang).stream().collect(Collectors.toMap(v->v.getI18nKey().getName(), v->v.getValue() == null ? "" : v.getValue()));
	}


	public Map<String, List<I18nValue>> findI18nValues(String groupName) {
		return i18nRepository.findAllByGroupName(groupName)
					.stream().collect(
							Collectors.groupingBy(v->v.getI18nKey().getName()));
	}


	public void saveI18nValues(Map<String, List<I18nValue>> i18nFields, String groupName) {
		
		Map<I18nKey, List<I18nValue>> keys = i18nRepository.findAllByGroupName(groupName).stream().collect(Collectors.groupingBy(I18nValue::getI18nKey));
		
		for (Entry<String, List<I18nValue>> entry: i18nFields.entrySet()) {			
			I18nKey i18nKey = keys.keySet().stream().filter(k->k.getName().equals(entry.getKey())).findFirst().orElse(new I18nKey(entry.getKey(), groupName));
			keys.remove(i18nKey);
			for (I18nValue value : entry.getValue()) {
				value.setI18nKey(i18nKey);
			}
		}
		i18nRepository.saveAll(i18nFields.values().stream().flatMap(list->list.stream()).collect(Collectors.toList()));
		for (Entry<I18nKey, List<I18nValue>> entry : keys.entrySet()) {
			i18nRepository.deleteValuesOf(entry.getKey());
			i18nRepository.deleteKeyOf(entry.getKey());
		}
	}


	public void deleteByGroupName(String groupName) {
		i18nRepository.deleteValuesByGroupName(groupName);
		i18nRepository.deleteKeyByGroupName(groupName);
	}


}
