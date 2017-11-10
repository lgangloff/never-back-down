package org.lgangloff.nbd.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.lgangloff.nbd.i18n.domain.I18nGroupNameKey;
import org.lgangloff.nbd.i18n.domain.I18nValue;

@Entity
@Table(name = "program")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Program extends AbstractAuditingEntity implements I18nGroupNameKey{

	@Id
	@GeneratedValue(generator = "program_seq")
	@SequenceGenerator(name = "program_seq", sequenceName = "program_id_seq", allocationSize = 20)
	private Long id;

	@Column(name = "name", length = 64, nullable = false, unique = false)
	private String name;

	@Column(name = "link", length = 64, nullable = false, unique = false)
	private String link;

	@Transient
	private Map<String, List<I18nValue>> i18nFields;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String getI18nGroupName() {
		return getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Map<String, List<I18nValue>> getI18nFields() {
		return i18nFields;
	}

	public void setI18nFields(Map<String, List<I18nValue>> i18nFields) {
		this.i18nFields = i18nFields;
	}

}
