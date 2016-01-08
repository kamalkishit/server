package com.humanize.server.authentication.service;

import java.io.StringWriter;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.humanize.server.Message;
import com.humanize.server.exception.EmailSendingException;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	private MimeMessage mimeMessage;
	private MimeMessageHelper mimeMessageHelper;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean sendEmail(Message message) throws EmailSendingException {
		//mimeMessage = javaMailSender.createMimeMessage();
		try {
			 final String username = "hello@humannize.com";
				final String password = "1@SHreyash";

				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");

		   // Get the default Session object.
				Session session = Session.getInstance(props,
						  new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username, password);
							}
						  });
				
				 mimeMessage = new MimeMessage(session);
			
			send(message);
			/*
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(message.getTo());
			mimeMessageHelper.setSubject(message.getSubject());
			mimeMessageHelper.setText(message.getBody());
			
			javaMailSender.send(mimeMessage);*/
			
		} catch(MessagingException exception) {
			logger.error("", exception);
			throw new EmailSendingException(0, null);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new EmailSendingException(0, null);
		}
		
		return true;
	}
	
	private void send(Message message) throws Exception {
	//mimeMessage = javaMailSender.createMimeMessage();
	  Template template = velocityEngine.getTemplate("./email.vm");
	
	  VelocityContext velocityContext = new VelocityContext();
	  velocityContext.put("email", "kamal@humannize.com");
	  velocityContext.put("link", message.getBody());
	   
	  StringWriter stringWriter = new StringWriter();
	  
	  template.merge(velocityContext, stringWriter);
	  
	  mimeMessage.setFrom(new InternetAddress("kamal@humannize.com"));
	  mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, message.getTo());
	  mimeMessage.setSubject("asdgasdgasgasdga platform to share and relive memorable travel experiences");
	  
	  mimeMessage.setText(stringWriter.toString());
	  mimeMessage.setContent(stringWriter.toString(), "text/html");
	  
	  //mailSender.send(message);
	  Transport.send(mimeMessage);
	}
}
