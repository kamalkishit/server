package com.humanize.server.gcm;

public enum ClientMessageType {

	CONTENT("content");
	
	String type;
	
	public String getType() {
		return type;
	}
	
	ClientMessageType(String type){
		
		this.type = type;
	}
}
