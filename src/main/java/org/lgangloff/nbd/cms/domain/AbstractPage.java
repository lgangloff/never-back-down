package org.lgangloff.nbd.cms.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.lgangloff.nbd.domain.AbstractAuditingEntity;
import org.lgangloff.nbd.i18n.domain.I18nGroupNameKey;

@MappedSuperclass
public abstract class AbstractPage extends AbstractAuditingEntity implements I18nGroupNameKey{

	public abstract Long getId();

    @Column(name = "name", length = 128, nullable = false, unique = true)
	private String name;
    
    @Column(name = "seo_name", length = 128, nullable = true, unique = true)
	private String seoName;
    
    
    
    @Override
	public String getI18nGroupName() {
		return name;
	}
    
	public I18nGroupNameKey getI18nMetaDataGroupKey() {
    	return ()->getI18nGroupName()+"-metadata";
    }

    @Column(name = "title", length = 64, nullable = false, unique = false)
	public String title;	

	public String description;
	public String keywords;
	public String author;
}
