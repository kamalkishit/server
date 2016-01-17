package com.humanize.server.helper;

import java.io.StringWriter;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanize.server.data.ContactUs;
import com.humanize.server.data.InviteUser;
import com.humanize.server.data.SuggestArticle;
import com.humanize.server.exception.EmailSendingException;

@Component
public class EmailHelper {
	
	private Properties properties;
	private String username;
	private String password;
	private Session session;
	private MimeMessage mimeMessage;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	public EmailHelper() {
		initialize();
	}
	
	private void initialize() {
		username = "kamal@humannize.com";
		password = "up25h9912";
		
		properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		mimeMessage = new MimeMessage(session);
	}
	
	public MimeMessage createInviteFriendMail(InviteUser inviteFriend) throws EmailSendingException {
		try {
			Template template = velocityEngine.getTemplate("./InviteFriend.vm");
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("email", "kamal@humannize.com");
			   
			StringWriter stringWriter = new StringWriter();
			  
			template.merge(velocityContext, stringWriter);
			  
			mimeMessage.setFrom(new InternetAddress("pandey.kishore@gmail.com"));
			mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, inviteFriend.getEmailId());
			mimeMessage.setSubject("Invitation");
			  
			mimeMessage.setText(stringWriter.toString());
			mimeMessage.setContent(stringWriter.toString(), "text/html");
			
			return mimeMessage;
		} catch (MessagingException exception) {
			exception.printStackTrace();
			throw new EmailSendingException(0, null);
		}
	}
	
	public MimeMessage createSuggestArticleMail(SuggestArticle suggestArticle) throws EmailSendingException {
		try { 
			mimeMessage.setFrom(new InternetAddress("pandey.kishore@gmail.com"));
			mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, "pandey.kishore@gmail.com");
			mimeMessage.setSubject("Suggest Article");

			mimeMessage.setContent(suggestArticle.getArticleUrl(), "text/html");
			
			return mimeMessage;
		} catch (MessagingException exception) {
			exception.printStackTrace();
			throw new EmailSendingException(0, null);
		}
	}
	
	public MimeMessage createContactUsMail(ContactUs contactUs) throws EmailSendingException {
		try { 
			mimeMessage.setFrom(contactUs.getEmailId());
			mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, "pandey.kishore@gmail.com");
			mimeMessage.setSubject("Contact Us");

			mimeMessage.setContent(contactUs.getBody(), "text/html");
			
			return mimeMessage;
		} catch (MessagingException exception) {
			exception.printStackTrace();
			throw new EmailSendingException(0, null);
		}
	}
}
