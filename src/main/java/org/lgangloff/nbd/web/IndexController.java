package org.lgangloff.nbd.web;

import java.util.List;
import java.util.Map;

import org.lgangloff.nbd.config.Constants;
import org.lgangloff.nbd.domain.Coach;
import org.lgangloff.nbd.domain.Program;
import org.lgangloff.nbd.domain.WebSiteConfig;
import org.lgangloff.nbd.domain.front.WebSite;
import org.lgangloff.nbd.domain.i18n.enumeration.LangKey;
import org.lgangloff.nbd.service.CoachService;
import org.lgangloff.nbd.service.I18nService;
import org.lgangloff.nbd.service.ProgramService;
import org.lgangloff.nbd.service.StorageService;
import org.lgangloff.nbd.service.WebSiteBuilder;
import org.lgangloff.nbd.service.WebSiteBuilder.SectionBuilder;
import org.lgangloff.nbd.service.WebSiteBuilder.SectionBuilder.RowBuilder;
import org.lgangloff.nbd.service.WebSiteBuilder.SectionBuilder.RowBuilder.ColCardBuilder;
import org.lgangloff.nbd.service.WebSiteBuilder.SectionBuilder.RowBuilder.ColEmptyBuilder;
import org.lgangloff.nbd.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	

	@Autowired
	private WebSiteService webSiteService;

	@Autowired
	private I18nService i18nService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private CoachService coachService;
	@Autowired
	private ProgramService programService;

	@RequestMapping({"/", "/index.html"})
	public String index(Model model) {
		
		WebSiteConfig webSiteConfig = webSiteService.getWebSiteConfig();
		Map<String, String> i18n = i18nService.findI18nValues(webSiteConfig.getName(), LangKey.en_EN);
		List<Coach> coachs = coachService.findAllForWebSite();
		List<Program> programs = programService.findAllForWebSite();
		
		WebSiteBuilder builder = WebSiteBuilder.newWebSite(Constants.WEBSITE_CONFIG_NAME, i18n.get("website.title"))
				.withBackGroundImage(storageService.getDownloadUrlOrDefault(webSiteConfig.getBackgroundImageFile(), "static/images/background.jpg"))
				.withLogoImage(
						storageService.getDownloadUrlOrDefault(webSiteConfig.getLogo500ImageFile(), "static/images/logo-500.png"),
						storageService.getDownloadUrlOrDefault(webSiteConfig.getLogo300ImageFile(), "static/images/logo-300.png"))
				.withSlogan(i18n.get("website.slogan"))
				.withMeta(i18n.get("website.meta.description"), i18n.get("website.meta.keywords"), i18n.get("website.meta.author"));
		
		buildSection(builder, coachs, "coachs", i18n.get("website.section.title.coachs"), (prevCol, nexColCss, coach)->{
			
			Map<String, String> coachi18n = i18nService.findI18nValues(coach.getName(), LangKey.en_EN);
			Map<String, String> competencesI18n = i18nService.findI18nValues(coach.getName() + "-competence", LangKey.en_EN);
			
			ColCardBuilder coachCard = prevCol
					.addColCard(nexColCss, coach.getName(), coach.getDisplayName(), coachi18n.get("coach.job.name"));
			
			if (coach.getPhoto() != null)
					coachCard.withImgTop(storageService.getDownloadUrl(coach.getPhoto()));
			
			for (String competence : competencesI18n.values()) {
				coachCard.addElement(competence);
			}
			
		});

		buildSection(builder, programs, "programs", i18n.get("website.section.title.programs"), (prevCol, nexColCss, program)->{

			Map<String, String>programi18n = i18nService.findI18nValues(program.getName(), LangKey.en_EN);
			
			prevCol
					.addColCard(nexColCss, program.getName(), programi18n.get("program.title"), programi18n.get("program.description"))
					.withFooterLink(programi18n.get("program.price.display"), program.getLink());
			
		});
					
		builder.addSection("contact", i18n.get("website.section.title.contact"))
						.row()
						.addColEmpty("col-sm-1")
						.addColCard("col-12 col-sm-10", "<p class=\"text-center\">\n" + 
								"                                    Laissez-nous votre email et votre message, nous vous répondrons rapidement.\n" + 
								"                                </p>\n" + 
								"                                <form action=\"https://getsimpleform.com/messages?form_api_token="+webSiteConfig.getFormContactKey()+"\" method=\"post\">\n" + 
								"                                    <div class=\"form-group\">\n" + 
								"                                        <label for=\"exampleInputEmail1\">Adresse mail</label>\n" + 
								"                                        <input type=\"email\" class=\"form-control\" id=\"exampleInputEmail1\" aria-describedby=\"emailHelp\" placeholder=\"Saississez votre adresse mail\" name=\"email\">\n" + 
								"                                        <small id=\"emailHelp\" class=\"form-text text-muted\">Nous ne partagerons jamais votre email à des tiers.</small>\n" + 
								"                                    </div>\n" + 
								"                                    <div class=\"form-group\">\n" + 
								"                                        <label for=\"exampleTextarea\">Message</label>\n" + 
								"                                        <textarea class=\"form-control\" id=\"exampleTextarea\" rows=\"3\" name=\"message\"></textarea>\n" + 
								"                                    </div>\n" + 
								"                                    <input type='submit' class=\"btn btn-primary float-right\"></input>\n" + 
								"                                </form>")
						
						.withFooter(i18n.get("website.contact.address"), i18n.get("website.contact.tel"), webSiteConfig.getEmail())
							.twitter(webSiteConfig.getTwitterUrl())
							.facebook(webSiteConfig.getFbUrl())
							.instagram(webSiteConfig.getInstaUrl());
			
		
				WebSite site = builder.build(); 
				model.addAttribute("site", site);
				return "neverbackdown";
		
	}

	
	public <T> void buildSection(WebSiteBuilder builder, List<T> elements, String sectionKey, String sectionTitle, CardBuilder<T> cardBuilder) {
		if (!elements.isEmpty()) {
			SectionBuilder coachsSection = builder
				.addSection(sectionKey, sectionTitle);
			
			RowBuilder row = null;

			//1 -> col-sm-4 col-lg-4 | col-12 col-sm-4 col-lg-4
			//2 ->          col-lg-2 | col-12 col-sm-6 col-lg-4
			//3 ->                   | col-12 col-sm-4 col-lg-4
			
			for (int i = 0; i < elements.size();) { //on n'incrémente pas i ici
				if (i%3 == 0) {
					row = coachsSection.row();
				}
				ColEmptyBuilder prevCol = null;
				String nexColCss = null;
				if (i+3<=elements.size()) {
					prevCol = row.addColEmpty("");
					nexColCss = "col-12 col-sm-4 col-lg-4";
				}
				else if (i+2<=elements.size()) {
					prevCol = row.addColEmpty("col-lg-2");
					nexColCss = "col-12 col-sm-6 col-lg-4";
				}
				else if (i+1<=elements.size()) {
					prevCol = row.addColEmpty("col-sm-4 col-lg-4");
					nexColCss = "col-12 col-sm-4 col-lg-4";
				}
				for (int j = 0; j < 3 && i < elements.size(); j++,i++) {
					T e = elements.get(i);
					
					cardBuilder.buildCard(prevCol, nexColCss, e);
				}
				
			}

		}
	}

	public interface CardBuilder<T> {
		void buildCard(ColEmptyBuilder prevCol, String nexColCss, T element);
	}
}
