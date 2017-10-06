package org.lgangloff.nbd.web;

import org.lgangloff.nbd.domain.front.CardBlock;
import org.lgangloff.nbd.service.WebSiteBuilder;

public class IndexController {

	
	public void index() {
		
		WebSiteBuilder.newWebSite("never-back-down", "Never Back Down - Programme")
			.addSection("coachs", "Coachs")
				.addCard("benoit", "Benoit Jacquemin", "Head Coach Crossfit Nancy")
					.withImgTop("static/images/benoit.jpg")
					.addElement("CrossFit Level 2 Trainer (CF-L2)")
					.addElement("Competitor Certificate (2017)")
					.addElement("Judge 2016 Certificate (2016)")
					.addElement("Judges Certificate (2017)")
					.addElement("Scaling Certificate (2016)")
					.addElement("Spot The Flaw Certificate (1)")
					.addElement("bpjeps agff 2014")
				.addCard("kevin", "Kevin Bouly", "Haltérophile français")
					.withImgTop("static/images/kevin.jpg")
					.addElement("8 fois champion de France")
					.addElement("Champion du Monde Masters")
					.addElement("12ème Jeux Olympiques")
			.addSection("programs", "Programs")
				.addCard("free", "Free", "Programmation du jour")
					.withFooterLink("#contact", "0€")
					.addCard("self-performing", "Self Performing", "1 Month<br/>\n" + 
							"                                        CYCLE<br/>\n" + 
							"                                        5 Jours<br/>\n" + 
							"                                        2 days<br/>\n" + 
							"                                        Weightlifting<br/>\n" + 
							"                                        Gym/Met-Con<br/>\n" + 
							"                                        Accesory work")
						.withFooterLink("#contact", "35€ / mois")

						.addCard("competitor", "Competitor", "\n" + 
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
							.withFooterLink("#contact", "50€ / mois")

							.addCard("personnal-training", "Personnal Training", "Weightlifting")
								.withFooterLink("#contact", "Sur devis")
								
					.addSection("contact", "Contact")
						.addCard("<p class=\"text-center\">\n" + 
								"                                    Laissez-nous votre email et votre message, nous vous répondrons rapidement.\n" + 
								"                                </p>\n" + 
								"                                <form action=\"https://getsimpleform.com/messages?form_api_token=0614d6cda955d996be8799845f653a8c\" method=\"post\">\n" + 
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
						
						.withFooter("Never Back Down", "26 Boulevard du 26ème RI, 54000 Nancy", "+336 12 23 45 69", "contact@never-backdown.fr")
							.twitter("url")
							.facebook("url")
							.instagram("url");
			
		
	}

}
