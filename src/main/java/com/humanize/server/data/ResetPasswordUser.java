package com.humanize.server.data;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ResetPasswordUser {
	
	private String emailId;
	private String tempPassword;
	private String newPassword;
	
	public String getEmailId() {
		return emailId;
	}
	
	@Required
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getTempPassword() {
		return tempPassword;
	}
	
	@Required
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	@Required
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
