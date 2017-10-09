package org.lgangloff.nbd.domain.front;

public class Link {
	public String name;
	public String href;
	public String text;
	
	public Link(String text, String href) {
		super();
		this.href = href;
		this.text = text;
	}

	public Link(String name, String text, String href) {
		this.name = name;
		this.href = href;
		this.text = text;
	}

	
}