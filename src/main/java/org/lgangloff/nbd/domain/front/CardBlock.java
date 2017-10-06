package org.lgangloff.nbd.domain.front;

public class CardBlock{


	public String title;
	public String text;
	
	public String html;

	public CardBlock(String title, String text) {
		super();
		this.title = title;
		this.text = text;
	}

	public CardBlock(String html) {
		super();
		this.html = html;
	}
	
	
}
