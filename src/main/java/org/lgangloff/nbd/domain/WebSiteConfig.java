package org.lgangloff.nbd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "web_site_config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WebSiteConfig extends AbstractAuditingEntity{

	@Id
    @GeneratedValue(generator = "web_site_config_seq")
    @SequenceGenerator(name = "web_site_config_seq",
            sequenceName = "web_site_config_id_seq", initialValue = 1, allocationSize = 20)
    private Long id;
	

    @Column(name = "name", length = 128, nullable = false, unique = false)
	private String name;
    
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="background_image_file_id")
    private File backgroundImageFile;
    

    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="logo_500_image_file_id")
    private File logo500ImageFile;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
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
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
    
    
}
