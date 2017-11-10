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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.lgangloff.nbd.i18n.domain.I18nGroupNameKey;
import org.lgangloff.nbd.i18n.domain.I18nValue;

@Entity
@Table(name = "web_site_config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WebSiteConfig extends AbstractAuditingEntity implements I18nGroupNameKey{

	@Id
    @GeneratedValue(generator = "web_site_config_seq")
    @SequenceGenerator(name = "web_site_config_seq",
            sequenceName = "web_site_config_id_seq", allocationSize = 20)
    private Long id;
	

    @Column(name = "name", length = 128, nullable = false, unique = false)
	private String name;
    
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true, cascade= {CascadeType.REMOVE})
    @JoinColumn(name="background_image_file_id")
    private File backgroundImageFile;
    

    @ManyToOne(fetch=FetchType.LAZY, optional=true, cascade= {CascadeType.REMOVE})
    @JoinColumn(name="logo_500_image_file_id")
    private File logo500ImageFile;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true, cascade= {CascadeType.REMOVE})
    @JoinColumn(name="logo_300_image_file_id")
    private File logo300ImageFile;
    

    
    @Column(name = "form_contact_key", length = 512, nullable = false, unique = false)
    private String formContactKey;
    

    @Column(name = "email", length = 512, nullable = false, unique = false)
    private String email;
    

    @Column(name = "fb_url", length = 512, nullable = true, unique = false)
    private String fbUrl;
    @Column(name = "twitter_url", length = 512, nullable = true, unique = false)
    private String twitterUrl;
    @Column(name = "insta_url", length = 512, nullable = true, unique = false)
    private String instaUrl;
    
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
		return name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public File getBackgroundImageFile() {
		return backgroundImageFile;
	}
	public void setBackgroundImageFile(File backgroundImageFile) {
		this.backgroundImageFile = backgroundImageFile;
	}
	public File getLogo500ImageFile() {
		return logo500ImageFile;
	}
	public void setLogo500ImageFile(File logo500ImageFile) {
		this.logo500ImageFile = logo500ImageFile;
	}
	public File getLogo300ImageFile() {
		return logo300ImageFile;
	}
	public void setLogo300ImageFile(File logo300ImageFile) {
		this.logo300ImageFile = logo300ImageFile;
	}
	public String getFormContactKey() {
		return formContactKey;
	}
	public void setFormContactKey(String formContactKey) {
		this.formContactKey = formContactKey;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFbUrl() {
		return fbUrl;
	}
	public void setFbUrl(String fbUrl) {
		this.fbUrl = fbUrl;
	}
	public String getTwitterUrl() {
		return twitterUrl;
	}
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}
	public String getInstaUrl() {
		return instaUrl;
	}
	public void setInstaUrl(String instaUrl) {
		this.instaUrl = instaUrl;
	}
	public Map<String, List<I18nValue>> getI18nFields() {
		return i18nFields;
	}
	public void setI18nFields(Map<String, List<I18nValue>> i18nFields) {
		this.i18nFields = i18nFields;
	}
	@Override
	public String toString() {
		return "WebSiteConfig [id=" + id + ", name=" + name + ", backgroundImageFile=" + backgroundImageFile
				+ ", logo500ImageFile=" + logo500ImageFile + ", logo300ImageFile=" + logo300ImageFile
				+ ", formContactKey=" + formContactKey + ", email=" + email + ", fbUrl=" + fbUrl + ", twitterUrl="
				+ twitterUrl + ", instaUrl=" + instaUrl + ", i18nFields=" + i18nFields + "]";
	}
    
    
}
