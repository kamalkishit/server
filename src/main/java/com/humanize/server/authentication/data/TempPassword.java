package com.humanize.server.authentication.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TempPassword {
	
	@Id
	private String id;

	@Indexed(unique = true)
	private String emailId;
	
	private String tempPassword;
	
	public TempPassword(String emailId, String tempPassword) {
		this.emailId = emailId;
		this.tempPassword = tempPassword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
}