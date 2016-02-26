package com.humanize.server.dao;

import com.humanize.server.data.VerificationCode;

public interface VerificationCodeRepositoryService {

	VerificationCode findByEmailId(String emailId);
}