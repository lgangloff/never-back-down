package org.lgangloff.nbd.domain.front;

import java.util.ArrayList;
import java.util.List;

public class Card {

	public Long id;
	public String imgSrcTop;
	public String name;
	public CardBlock block;
	
	public List<String> items = new ArrayList<>();
	
	public String linkHref, linkText ;

	public Card(String name, String title, String text) {
		super();
		this.name = name;
		this.block = new CardBlock(title, text);
	}

	public Card(String html) {
		super();
		this.block = new CardBlock(html);
	}

	public void setImgSrcTop(String imgSrcTop) {
		this.imgSrcTop = imgSrcTop;
	}

	public void addItem(String text) {
		this.items.add(text);
	}

	public void setLink(String href, String text) {
		this.linkHref = href;
		this.linkText = text;
	}

}
