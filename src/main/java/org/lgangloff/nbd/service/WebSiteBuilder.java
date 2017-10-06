package org.lgangloff.nbd.service;

import org.lgangloff.nbd.domain.front.Card;
import org.lgangloff.nbd.domain.front.Section;
import org.lgangloff.nbd.domain.front.WebSite;

public class WebSiteBuilder {


	private WebSite site;
	
	
	public  class SectionBuilder {
		Section section;

		public  class CardBuilder {
			Card card;

			public CardBuilder newCard(String name, String title, String text) {
				card = new Card(name, title, text);
				section.addCard(card);
				return this;
			}

			public CardBuilder newCard(String html) {
				card = new Card(html);
				section.addCard(card);
				return this;
			}

			public CardBuilder  withImgTop(String src) {
				card.setImgSrcTop(src);
				return this;
			}

			public CardBuilder addElement(String text) {
				card.addItem(text);
				return this;
			}
			
			public CardBuilder withFooterLink(String href, String text) {
				card.setLink(href, text);
				return this;
			}

			public CardBuilder addCard(String name, String title, String text) {
				return SectionBuilder.this.addCard(name, title, text);
			}

			public SectionBuilder addSection(String name, String title) {
				return WebSiteBuilder.this.addSection(name, title);
			}

			
		}
		
		public  SectionBuilder newSection(String name, String title) {
			 section = new Section(name, title);
			 site.addSection(section);
			 return this;
		}
		public CardBuilder addCard(String name, String title, String text) {
			return new CardBuilder().newCard(name, title, text);
		}

		public CardBuilder addCard(String html) {
			return new CardBuilder().newCard(html);
		}

	}
	
	public static  WebSiteBuilder newWebSite(String name, String title) {
		WebSiteBuilder builder = new WebSiteBuilder();
		builder.site = new WebSite(name, title);
		return builder;
	}
	
	public SectionBuilder addSection(String name, String title) {
		return new SectionBuilder().newSection(name, title);
	}
}
