package com.humanize.server.data;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SignupUser {
	
	private String emailId;
	private String invitationCode;
	private String password;
	
	public String getEmailId() {
		return emailId;
	}
	
	@Required
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getInvitationCode() {
		return invitationCode;
	}
	
	@Required
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Required
	public void setPassword(String password) {
		this.password = password;
	}
}
