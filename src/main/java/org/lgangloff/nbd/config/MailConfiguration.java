package org.lgangloff.nbd.config;

import java.io.IOException;

import org.lgangloff.nbd.service.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Configuration
public class MailConfiguration {

	private final Logger log = LoggerFactory.getLogger(MailConfiguration.class);

    @Autowired
    private Environment env;

	@Bean
	@Description("Thymeleaf template resolver serving HTML 5 emails")
	public ClassLoaderTemplateResolver emailTemplateResolver() {
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix("templates/");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode("HTML");
		emailTemplateResolver.setCharacterEncoding("UTF-8");
		emailTemplateResolver.setOrder(1);
		return emailTemplateResolver;
	}

	@Bean
	@ConditionalOnProperty("mail.sendgrid.api.key")
	public MailSender sendGridMailSender() {
		log.debug("Configuring sendgrid mail server");
		return new MailSender() {

			@Override
			@Async
			public void sendEmail(String fromStr, String toStr, String subject, String contentStr, boolean isMultipart,
					boolean isHtml) {

				Email from = new Email(fromStr);
				Email to = new Email(toStr);
				Content content = new Content(isHtml ? "text/html" : "text/plain", contentStr);

				Mail mail = new Mail(from, subject, to, content);

		    	String apiKey = env.getProperty("mail.sendgrid.api.key");
				SendGrid sg = new SendGrid(apiKey);
				Request request = new Request();
				try {
					request.setMethod(Method.POST);
					request.setEndpoint("mail/send");
					request.setBody(mail.build());
					Response response = sg.api(request);
					System.out.println(response.getStatusCode());
					System.out.println(response.getBody());
					System.out.println(response.getHeaders());
				} catch (IOException e) {
					log.warn("E-mail could not be sent to user '{}' with SendGrid, exception is: {}", to,
							e.getMessage());
				}
			}
		};		
	}


	@Bean
	@ConditionalOnProperty(matchIfMissing= true, name="mail.sendgrid.api.key")
	public MailSender logMailSender() {
		log.debug("Configuring LOG  mail server");
		return new MailSender() {
			@Override
			public void sendEmail(String from, String to, String subject, String content, boolean isMultipart, boolean isHtml) {
				log.info("Send mail to {} from {} with subject {} and content {}", to, from, subject, content);
			}
		};
	}
}
