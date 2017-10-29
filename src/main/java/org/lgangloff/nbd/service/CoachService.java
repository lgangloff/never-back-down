package org.lgangloff.nbd.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

	public Coach saveOrUpdate(Coach coach) {
		if (coach.getId()  == null) {
			coach.setName(Normalizer.normalize(coach.getDisplayName(), Normalizer.Form.NFD).replaceAll(" ", "-").toLowerCase());
		}

		storageService.markAsUsed(coach.getPhoto());

		if (coach.getI18nFields() != null)
			i18nService.saveI18nValues(coach.getI18nFields(), coach.getName());

		if (coach.getCompetenceI18nFields() != null) {
			i18nService.saveI18nValues(coach.getCompetenceI18nFields(), coach.getName() + "-competence");
		}
			
		coachRepository.save(coach);
		
		return coach;
	}

	public List<Coach> findAll(String query) {
		return coachRepository.findAll(query);
	}
	public List<Coach> findAllForWebSite() {
		return findAll("").stream().map(withI18nFields()).collect(Collectors.toList());
	}

	public Optional<Coach> findOne(Long id) {
		Optional<Coach> coach = coachRepository.findOne(id);
		return coach.map(withI18nFields());
	}

	private Function<? super Coach, ? extends Coach> withI18nFields() {
		return c->{
			c.setI18nFields(i18nService.findI18nValues(c.getName()));
			c.setCompetenceI18nFields(i18nService.findI18nValues(c.getName() + "-competence"));
			return c;
		};
	}

	public void deleteUser(Long id) {
		Optional<Coach> optCoach = coachRepository.findOne(id);
		if (!optCoach.isPresent()) {
			return;
		}
		Coach coach = optCoach.get();
		i18nService.deleteByGroupName(coach.getName());
		i18nService.deleteByGroupName(coach.getName()+ "-competence");
		if (coach.getPhoto() != null)
			storageService.deleteFile(coach.getPhoto().getId());
		coachRepository.deleteById(id);
	}

}
