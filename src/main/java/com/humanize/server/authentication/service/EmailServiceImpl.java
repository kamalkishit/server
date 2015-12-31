package com.humanize.server.authentication.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
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
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(message.getTo());
			mimeMessageHelper.setSubject(message.getSubject());
			//mimeMessageHelper.setText(message.getBody());
			
			/*VelocityEngine ve = new VelocityEngine();
	        ve.init();
	        Template t = ve.getTemplate("invitecode.vm");
	        VelocityContext context = new VelocityContext();
	        context.put("name", "World");
	        StringWriter writer = new StringWriter();
	        t.merge( context, writer );
	        
	        mimeMessageHelper.setText(writer.toString());*/
	        
	        Map<String, Object> model = new HashMap<String, Object>();
			String str = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
					"invitecode.vm", model);
			
			mimeMessageHelper.setText(str, true);
			
			javaMailSender.send(mimeMessage);
			
		} catch(MessagingException exception) {
			logger.error("", exception);
			throw new SendEmailException(0, null);
		}
		
		return true;
	}
}
