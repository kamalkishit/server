package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.utility.RandomStringGenerator;

@Service
public class RandomStringGeneratorService {
	
	@Autowired
	private RandomStringGenerator randomStringGenerator;
	
	public String getTempPassword() {
		return randomStringGenerator.generateRandomString(10);
	}
	
	public String getVerificationCode() {
		return randomStringGenerator.generateRandomString(25);
	}
	
	public String getInvitationCode() {
		return randomStringGenerator.generateRandomString(25);
	}
}
