package com.humanize.server.authentication.service;

import com.humanize.server.authentication.data.VerificationCode;
import com.humanize.server.authentication.exception.VerificationCodeCreationException;
import com.humanize.server.authentication.exception.VerificationCodeDeletionException;
import com.humanize.server.authentication.exception.VerificationCodeNotFoundException;
import com.humanize.server.authentication.exception.VerificationCodeUpdationException;

public interface VerificationCodeRepositoryService {
	
	public VerificationCode findByEmailId(String emailId) throws VerificationCodeNotFoundException;
	public VerificationCode create(VerificationCode verificationCode) throws VerificationCodeCreationException;
	public VerificationCode createOrUpdate(VerificationCode verificationCode) throws VerificationCodeCreationException, VerificationCodeUpdationException;
	public VerificationCode update(VerificationCode verificationCode) throws VerificationCodeUpdationException;
	public void deleteByEmailId(String emailId) throws VerificationCodeDeletionException;
	public void delete(VerificationCode verificationCode);
}
