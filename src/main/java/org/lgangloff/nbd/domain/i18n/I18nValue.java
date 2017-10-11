package org.lgangloff.nbd.domain.i18n;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.lgangloff.nbd.domain.AbstractAuditingEntity;
import org.lgangloff.nbd.domain.i18n.enumeration.LangKey;


@Entity
@Table(name = "i18n_value")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class I18nValue extends AbstractAuditingEntity {


	@Id
    @GeneratedValue(generator = "i18n_value_seq")
    @SequenceGenerator(name = "i18n_value_seq",
            sequenceName = "i18n_value_id_seq", initialValue = 1, allocationSize = 20)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i18n_key_id")
	private I18nKey i18nKey;
    
    @Column(name="lang_key")
    @Enumerated(EnumType.STRING)
	private LangKey langKey;

	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public I18nKey getI18nKey() {
		return i18nKey;
	}

	public void setI18nKey(I18nKey i18nKey) {
		this.i18nKey = i18nKey;
	}

	public LangKey getLangKey() {
		return langKey;
	}

	public void setLangKey(LangKey langKey) {
		this.langKey = langKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
