package org.lgangloff.nbd.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.lgangloff.nbd.domain.Program;
import org.lgangloff.nbd.exception.FieldRequiredException;
import org.lgangloff.nbd.repository.ProgramRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProgramService {

	private final Logger log = LoggerFactory.getLogger(ProgramService.class);

	@Autowired
	private ProgramRepository programRepository;

	@Autowired
	private I18nService i18nService;

	public Program saveOrUpdate(Program p) throws FieldRequiredException {
		if (p.getId()  == null) {
			p.setName(Normalizer.normalize(p.getI18nFields().get("program.title")
					.stream().filter(i18n->StringUtils.isNotEmpty(i18n.getValue())).findFirst()
					.orElseThrow(() -> new FieldRequiredException("program.title")).getValue(), Normalizer.Form.NFD).replaceAll(" ", "-").toLowerCase());
		}

		if (p.getI18nFields() != null)
			i18nService.saveI18nValues(p.getI18nFields(), p.getName());

		Program program = programRepository.save(p);
		
		return program;
	}

	public List<Program> findAll(String query) {
		return programRepository.findAll();
	}
	
	public Optional<Program> findOne(Long id) {
		Optional<Program> coach = programRepository.findById(id);
		return coach.map(withI18nFields());
	}

	private Function<? super Program, ? extends Program> withI18nFields() {
		return p->{
			p.setI18nFields(i18nService.findI18nValues(p.getName()));
			return p;
		};
	}

	public void deleteProgram(Long id) {
		Optional<Program> optProgram = programRepository.findById(id);
		if (!optProgram.isPresent()) {
			return;
		}
		Program program = optProgram.get();
		i18nService.deleteByGroupName(program.getName());
		programRepository.deleteById(id);
	}

	public List<Program> findAllForWebSite() {
		return findAll("%");
	}

}
