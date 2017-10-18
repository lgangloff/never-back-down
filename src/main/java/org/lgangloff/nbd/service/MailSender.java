package org.lgangloff.nbd.service;


public interface MailSender {

	void sendEmail(String from, String to, String subject, String content,
			boolean isMultipart, boolean isHtml);
}
