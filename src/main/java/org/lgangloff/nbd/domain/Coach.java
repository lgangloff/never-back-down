package org.lgangloff.nbd.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.lgangloff.nbd.i18n.domain.I18nGroupNameKey;
import org.lgangloff.nbd.i18n.domain.I18nValue;

@Entity
@Table(name = "coach")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Coach extends AbstractAuditingEntity implements  I18nGroupNameKey{

	@Id
    @GeneratedValue(generator = "coach_seq")
    @SequenceGenerator(name = "coach_seq",
            sequenceName = "coach_id_seq", allocationSize = 20)
    private Long id;
	

    @Column(name = "name", length = 64, nullable = false, unique = false)
	private String name;
    
    @Size(max = 50)
    @Column(name = "display_name", length = 64)
    private String displayName;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true, cascade= {CascadeType.REMOVE})
    @JoinColumn(name="photo_file_id")
    private File photo;
    

    @Transient
    private Map<String, List<I18nValue>> i18nFields;

    @Transient
    private Map<String, List<I18nValue>> competenceI18nFields;

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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public Map<String, List<I18nValue>> getI18nFields() {
		return i18nFields;
	}

	public void setI18nFields(Map<String, List<I18nValue>> i18nFields) {
		this.i18nFields = i18nFields;
	}

	public Map<String, List<I18nValue>> getCompetenceI18nFields() {
		return competenceI18nFields;
	}

	public void setCompetenceI18nFields(Map<String, List<I18nValue>> competenceI18nFields) {
		this.competenceI18nFields = competenceI18nFields;
	}

	public I18nGroupNameKey getI18nCompetenceGroupKey() {
		return ()->name + "-competence";
	}
    
    
}
