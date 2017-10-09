package org.lgangloff.nbd.service;

import org.lgangloff.nbd.domain.front.Card;
import org.lgangloff.nbd.domain.front.Col;
import org.lgangloff.nbd.domain.front.Row;
import org.lgangloff.nbd.domain.front.Section;
import org.lgangloff.nbd.domain.front.WebSite;

public class WebSiteBuilder {


	private WebSite site;
	
	
	public  class SectionBuilder {
		Section section;

		public class RowBuilder{
			Row row;

			public  class ColEmptyBuilder {
				Col col;
				
				public ColEmptyBuilder newCol(String colCss) {
					col = new Col(colCss);
					row.addCol(col);
					return this;
				}

				public ColCardBuilder addColCard(String css, String name, String title, String text) {
					return RowBuilder.this.addColCard(css, name, title, text);
				}
				public ColCardBuilder addColCard(String css, String html) {
					return RowBuilder.this.addColCard(css, html);
				}
			}
			
			public  class ColCardBuilder {
				Col col;
				Card card;

				public ColCardBuilder newCard(String colCss, String name, String title, String text) {
					card = new Card(name, title, text);
					col = new Col(colCss, card);
					row.addCol(col);
					return this;
				}

				public ColCardBuilder newCard(String colCss, String html) {
					card = new Card(html);
					col = new Col(colCss, card);
					row.addCol(col);
					return this;
				}

				public ColCardBuilder  withImgTop(String src) {
					card.setImgSrcTop(src);
					return this;
				}

				public ColCardBuilder addElement(String text) {
					card.addItem(text);
					return this;
				}
				
				public ColCardBuilder withFooterLink(String text, String href) {
					card.setLink(text, href);
					return this;
				}


				public SectionBuilder addSection(String name, String title) {
					return WebSiteBuilder.this.addSection(name, title);
				}

				public WebSiteBuilder  withFooter(String address, String tel, String email) {
					return WebSiteBuilder.this.withFooter(address, tel, email);
				}
				
				public ColCardBuilder addColCard(String css, String name, String title, String text) {
					return RowBuilder.this.addColCard(css, name, title, text);
				}

				public ColCardBuilder addColCard(String css, String html) {
					return RowBuilder.this.addColCard(css, html);
				}

				
			}
			


			public RowBuilder newRow() {
				row = new Row("");
				section.addRow(row);
				return this;
			}
			
			public ColCardBuilder addColCard(String css, String name, String title, String text) {
				return new ColCardBuilder().newCard(css, name, title, text);
			}

			public ColCardBuilder addColCard(String css, String html) {
				return new ColCardBuilder().newCard(css, html);
			}

			public ColEmptyBuilder addColEmpty(String css) {
				return new ColEmptyBuilder().newCol(css);
			}
		}
		
		
		public  SectionBuilder newSection(String name, String title) {
			 section = new Section(name, title);
			 site.addSection(section);
			 return this;
		}
		
		public RowBuilder row() {
			return new RowBuilder().newRow();
		}

	}
	
	public static  WebSiteBuilder newWebSite(String name, String title) {
		WebSiteBuilder builder = new WebSiteBuilder();
		builder.site = new WebSite(name, title);
		return builder;
	}

	public WebSiteBuilder  withFooter(String address, String tel, String email) {
		site.setContactInformation(address, tel, email);
		return this;
	}

	public SectionBuilder addSection(String name, String title) {
		return new SectionBuilder().newSection(name, title);
	}

	public WebSiteBuilder  twitter(String url) {
		site.addFooterLink("twitter", "Twitter",  url);
		return this;
	}

	public WebSiteBuilder facebook(String url) {
		site.addFooterLink("facebook", "Facebook",  url);
		return this;
	}

	public WebSiteBuilder instagram(String url) {
		site.addFooterLink("instagram", "Instagram",  url);
		return this;
	}

	public WebSite build() {
		return site;
	}

	public WebSiteBuilder withSlogan(String slogan) {
		this.site.setSlogan(slogan);
		return this;
	}

	public WebSiteBuilder withBackGroundImage(String url) {
		this.site.setBackGroundImage(url);
		return this;
	}

	public WebSiteBuilder withLogoImage(String url500, String url300) {
		this.site.setLogoImage(url500, url300);
		return this;
	}
}
