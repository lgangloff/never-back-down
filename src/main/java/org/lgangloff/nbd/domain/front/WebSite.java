package org.lgangloff.nbd.domain.front;

import java.util.ArrayList;
import java.util.List;

public class WebSite {

	public Long id;
	public String name;
	
	public String title;
	
	public List<Section> sections = new ArrayList<>();

	public WebSite(String name, String title) {
		super();
		this.name = name;
		this.title = title;
	}
	
	public  void addSection(Section e) {
		this.sections.add(e);
	}
}
