package com.humanize.server.dao;

import com.humanize.server.data.PasswordVerificationCode;
import com.humanize.server.exception.PasswordVerificationCodeNotFoundException;

public interface PasswordVerificationCodeRepositoryService {

	PasswordVerificationCode findByEmailId(String emailId) throws PasswordVerificationCodeNotFoundException;
}
