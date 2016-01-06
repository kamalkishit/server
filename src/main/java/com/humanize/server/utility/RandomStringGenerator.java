package com.humanize.server.utility;

import org.springframework.stereotype.Service;

@Service
public class RandomStringGenerator {
	
	private String characters;
	private StringBuffer buffer;
	
	public RandomStringGenerator() {
		this.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	}
	
	public String generateRandomString(int length) {
		buffer = new StringBuffer();
		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		
		return buffer.toString();
	}
}
