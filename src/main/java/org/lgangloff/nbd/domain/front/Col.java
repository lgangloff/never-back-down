package org.lgangloff.nbd.domain.front;

public class Col {
	public String css;
	public Card card;

	public Col(String css) {
		this.css = css;
	}
	
	public Col(String css, Card card) {
		this.css = css;
		this.card =card;
	}	
}