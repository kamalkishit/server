package com.humanize.server.authentication.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class InvitationCode {

	@Id
	private String id;

	@Indexed(unique = true)
	private String emailId;
	
	private String invitedBy;
	
	private String invitationCode;
	
	public InvitationCode(String emailId, String invitedBy, String invitationCode) {
		this.emailId = emailId;
		this.invitedBy = invitedBy;
		this.invitationCode = invitationCode;
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

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getInvitedBy() {
		return invitedBy;
	}

	public void setInvitedBy(String invitedBy) {
		this.invitedBy = invitedBy;
	}
}
