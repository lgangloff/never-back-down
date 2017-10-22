package org.lgangloff.nbd.service;

import java.text.Normalizer;
import java.util.List;

import org.lgangloff.nbd.config.Constants;
import org.lgangloff.nbd.domain.Coach;
import org.lgangloff.nbd.repository.CoachRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoachService {

	private final Logger log = LoggerFactory.getLogger(CoachService.class);

	@Autowired
	private CoachRepository coachRepository;

	@Autowired
	private StorageService storageService;
	@Autowired
	private I18nService i18nService;

	public List<Coach> findAllCoach() {
		return coachRepository.findAll();
	}

	public void update(Coach coach) {
		if (coach.getId()  == null) {
			coach.setName(Normalizer.normalize(coach.getDiplayName(), Normalizer.Form.NFD).replaceAll(" ", "-"));
		}

		storageService.markAsUsed(coach.getPhoto());

		i18nService.saveI18nValues(coach.getI18nFields(), coach.getName());
		i18nService.saveI18nValues(coach.getCompetenceI18nFields(), coach.getName() + "-competence");
		
		coachRepository.save(coach);
	}
	
}
