package org.lgangloff.nbd.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lgangloff.nbd.config.Constants;
import org.lgangloff.nbd.domain.Coach;
import org.lgangloff.nbd.domain.WebSiteConfig;
import org.lgangloff.nbd.domain.front.WebSite;
import org.lgangloff.nbd.domain.i18n.enumeration.LangKey;
import org.lgangloff.nbd.service.CoachService;
import org.lgangloff.nbd.service.I18nService;
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

	@RequestMapping({"/", "/index.html"})
	public String index(Model model) {
		
		WebSiteConfig webSiteConfig = webSiteService.getWebSiteConfig();
		Map<String, String> i18n = i18nService.findI18nValues(webSiteConfig.getName(), LangKey.en_EN);
		List<Coach> coachs = coachService.findAllForWebSite();
		
		WebSiteBuilder builder = WebSiteBuilder.newWebSite(Constants.WEBSITE_CONFIG_NAME, i18n.get("website.title"))
				.withBackGroundImage(storageService.getDownloadUrlOrDefault(webSiteConfig.getBackgroundImageFile(), "static/images/background.jpg"))
				.withLogoImage(
						storageService.getDownloadUrlOrDefault(webSiteConfig.getLogo500ImageFile(), "static/images/logo-500.png"),
						storageService.getDownloadUrlOrDefault(webSiteConfig.getLogo300ImageFile(), "static/images/logo-300.png"))
				.withSlogan(i18n.get("website.slogan"))
				.withMeta(i18n.get("website.meta.description"), i18n.get("website.meta.keywords"), i18n.get("website.meta.author"));
		
		if (!coachs.isEmpty()) {
			int size = coachs.size();
			RowBuilder coachsSection = builder
				.addSection("coachs", i18n.get("website.section.title.coachs")).row();
			
			int colSize = 12 / size;
			ColEmptyBuilder prevCol = coachsSection.addColEmpty("col-lg-2");
			
			for (Coach coach : coachs) {
				Map<String, String> coachi18n = i18nService.findI18nValues(coach.getName(), LangKey.en_EN);
				Map<String, String> competencesI18n = i18nService.findI18nValues(coach.getName() + "-competence", LangKey.en_EN);
				
				ColCardBuilder coachCard = prevCol
					.addColCard("col-12 col-sm-6 col-lg-4", coach.getName(), coach.getDisplayName(), coachi18n.get("coach.job.name"))
					.withImgTop(storageService.getDownloadUrl(coach.getPhoto()));
				
				for (String competence : competencesI18n.values()) {
					coachCard.addElement(competence);
				}
			}
			
					.addColCard("col-12 col-sm-6 col-lg-4", "benoit", "Benoit Jacquemin", "Head Coach Crossfit Nancy")
						.withImgTop("static/images/benoit.jpg")
						.addElement("CrossFit Level 2 Trainer (CF-L2)")
						.addElement("Competitor Certificate (2017)")
						.addElement("Judge 2016 Certificate (2016)")
						.addElement("Judges Certificate (2017)")
						.addElement("Scaling Certificate (2016)")
						.addElement("Spot The Flaw Certificate (1)")
						.addElement("bpjeps agff 2014")
					.addColCard("col-12 col-sm-6 col-lg-4", "kevin", "Kevin Bouly", "Haltérophile français")
						.withImgTop("static/images/kevin.jpg")
						.addElement("8 fois champion de France")
						.addElement("Champion du Monde Masters")
						.addElement("12ème Jeux Olympiques");

		}
					
		builder
			.addSection("programs", i18n.get("website.section.title.programs"))
				.row()
				.addColEmpty("col-lg-1")
				.addColCard("col-12 col-sm-4 col-lg-2", "free", "Free", "Programmation du jour")
					.withFooterLink("0€", "#contact")
					.addColCard("col-12 col-sm-4 col-lg-2", "self-performing", "Self Performing", "1 Month<br/>\n" + 
							"                                        CYCLE<br/>\n" + 
							"                                        5 Jours<br/>\n" + 
							"                                        2 days<br/>\n" + 
							"                                        Weightlifting<br/>\n" + 
							"                                        Gym/Met-Con<br/>\n" + 
							"                                        Accesory work")
						.withFooterLink("35€ / mois", "#contact")

						.addColCard("col-12 col-sm-4 col-lg-2", "competitor", "Competitor", "\n" + 
								"                                        3 Month<br/>\n" + 
								"                                        CYCLE<br/>\n" + 
								"                                        7 jours<br/>\n" + 
								"                                        PROGRAMING<br/>\n" + 
								"                                        3 /4 days<br/>\n" + 
								"                                        Weightlifting<br/>\n" + 
								"                                        Accesory work<br/>\n" + 
								"                                        Gym<br/>\n" + 
								"                                        Metabolic<br/>\n" + 
								"                                        conditionnement<br/>\n" + 
								"                                        RECOVERY<br/>\n" + 
								"                                        Functional<br/>\n" + 
								"                                        movement :Injury<br/>\n" + 
								"                                        Prevention")
							.withFooterLink("50€ / mois", "#contact")

							.addColCard("col-12 col-sm-6 col-lg-2", "personnal-training", "Personnal Training", "Weightlifting")
								.withFooterLink("Sur devis", "#contact")
								
							.addColCard("col-12 col-sm-6 col-lg-2", "personnal-training-hour", "Personnal Training", "1 hour")
								.withFooterLink("50€ / mois", "#contact")
								
					.addSection("contact", i18n.get("website.section.title.contact"))
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

}
