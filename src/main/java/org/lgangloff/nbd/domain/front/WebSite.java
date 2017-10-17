package org.lgangloff.nbd.domain.front;

import java.util.ArrayList;
import java.util.List;

public class WebSite {

	public Long id;
	public String name;
	
	public String title;	
	public String slogan;
	
	public String backGroundImage;
	public String logoImage500, logoImage300;
	
	public List<Section> sections = new ArrayList<>();
	
	public String contactAddress;
	public String contactTel;
	public String contactEmail;
	
	public List<Link> footerLinks = new ArrayList<>();
	

	public WebSite(String name, String title) {
		super();
		this.name = name;
		this.title = title;
	}
	
	
	
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}



	public  void addSection(Section e) {
		this.sections.add(e);
	}

	public void setContactInformation(String address, String tel, String email) {
		this.contactAddress = address;
		this.contactTel = tel;
		this.contactEmail = email;
	}

	public void addFooterLink(String name, String text, String href) {
		this.footerLinks.add(new Link(name, text, href));
	}



	public void setBackGroundImage(String url) {
		this.backGroundImage = url;
	}



	public void setLogoImage(String url500, String url300) {
		this.logoImage500 = url500;
		this.logoImage300 = url300;
	}
}
