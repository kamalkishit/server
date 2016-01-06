package com.humanize.server.authentication.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.authentication.data.InvitationCode;

public interface InvitationCodeRepository extends MongoRepository<InvitationCode, String>{
	
	InvitationCode findByEmailId(String emailId);
}
