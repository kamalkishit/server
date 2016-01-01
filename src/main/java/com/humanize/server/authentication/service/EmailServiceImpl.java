package com.humanize.server.authentication.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
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
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.humanize.server.Message;
import com.humanize.server.exception.SendEmailException;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	private MimeMessage mimeMessage;
	private MimeMessageHelper mimeMessageHelper;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean sendEmail(Message message) throws SendEmailException {
		mimeMessage = javaMailSender.createMimeMessage();
		try {
			
			send(message.getTo());
			/*
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(message.getTo());
			mimeMessageHelper.setSubject(message.getSubject());
			mimeMessageHelper.setText(message.getBody());
			
			javaMailSender.send(mimeMessage);*/
			
		} catch(MessagingException exception) {
			logger.error("", exception);
			throw new SendEmailException(0, null);
		} catch (Exception exception) {
			throw new SendEmailException(0, null);
		}
		
		return true;
	}
	
	private void send(String emailId) throws Exception {
		mimeMessage = javaMailSender.createMimeMessage();
	  Template template = velocityEngine.getTemplate("./invitecode.vm");
	
	  VelocityContext velocityContext = new VelocityContext();
	   
	  StringWriter stringWriter = new StringWriter();
	  
	  template.merge(velocityContext, stringWriter);
	  
	  mimeMessage.setFrom(new InternetAddress("kamal@humannize.com"));
	  mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, emailId);
	  mimeMessage.setSubject("Welcome to Triplived, a platform to share and relive memorable travel experiences");
	  
	  mimeMessage.setText(stringWriter.toString());
	  mimeMessage.setContent(stringWriter.toString(), "text/html");
	  
	  //mailSender.send(message);
	  javaMailSender.send(mimeMessage);
	}
}
