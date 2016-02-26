package com.humanize.server.data;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SignupUser {
	
	@Email
	private String emailId;
	private String password;
	
	public String getEmailId() {
		return emailId;
	}
	
	@Required
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Required
	public void setPassword(String password) {
		this.password = password;
	}
}
