package org.lgangloff.nbd.mail;


public interface MailSender {

	void sendEmail(String from, String to, String subject, String content,
			boolean isMultipart, boolean isHtml);
}
