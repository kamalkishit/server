package com.humanize.server.authentication.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VerificationCode {
	
	@Id
	private String id;

	@Indexed(unique = true)
	private String emailId;
	
	private String verificationCode;
	
	public VerificationCode(String emailId, String verificationCode) {
		this.emailId = emailId;
		this.verificationCode = verificationCode;
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

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
