package org.lgangloff.nbd.service;

import java.util.Locale;

import org.lgangloff.nbd.domain.User;
import org.lgangloff.nbd.domain.WebSiteConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * Service for sending e-mails.
 * <p/>
 * <p>
 * We use the @Async annotation to send e-mails asynchronously.
 * </p>
 */
@Service
public class MailService {

	private final Logger log = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private Environment env;

	@Autowired
	private WebSiteService webSiteService;
	
	@Autowired
	private MailSender mailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Async
	public void sendPassword(User user,  String clearPassword) {
		log.debug("Sending password e-mail to '{}' with clearpassword {}", user.getLogin(), clearPassword);
		Locale locale = Locale.forLanguageTag(user.getLangKey() == null ? "fr" : user.getLangKey());
		Context context = new Context(locale);
		context.setVariable("user", user);
		context.setVariable("clearPassword", clearPassword);
		context.setVariable("appwebsite", env.getProperty("app.url"));

		WebSiteConfig config = webSiteService.getWebSiteConfig();
		
		String content = templateEngine.process("new-password", context);
		String subject = "Information de connexion";
		
		mailSender.sendEmail(config.getEmail(), user.getLogin(), subject, content, false, true);
	}

}
