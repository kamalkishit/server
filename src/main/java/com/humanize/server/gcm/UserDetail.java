package com.humanize.server.gcm;

public class UserDetail {
	
	private String regId;
	
	public UserDetail(){
	}

	public UserDetail(String regId) {
		this.regId = regId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}
}
