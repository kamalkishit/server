package com.humanize.server.utility;

import org.springframework.stereotype.Service;

@Service
public class RandomStringGenerator {
	
	String characters;
	StringBuffer buffer;
	
	public RandomStringGenerator() {
		this.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		this.buffer = new StringBuffer();
	}
	
	public String generateRandomString(int length) {
		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		
		return buffer.toString();
	}
}
