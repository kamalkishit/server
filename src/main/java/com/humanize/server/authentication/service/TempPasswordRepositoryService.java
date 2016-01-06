package com.humanize.server.authentication.service;

import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.exception.TempPasswordCreationException;
import com.humanize.server.authentication.exception.TempPasswordDeletionException;
import com.humanize.server.authentication.exception.TempPasswordNotFoundException;
import com.humanize.server.authentication.exception.TempPasswordUpdationException;

public interface TempPasswordRepositoryService {

	TempPassword create(TempPassword tempPassword) throws TempPasswordCreationException;
	TempPassword createOrUpdate(TempPassword tempPassword) throws TempPasswordCreationException, TempPasswordUpdationException;
	TempPassword update(TempPassword tempPassword) throws TempPasswordUpdationException;
	TempPassword findByEmailId(String emailId) throws TempPasswordNotFoundException;
	void delete(String emailId) throws TempPasswordDeletionException;
	void delete(TempPassword tempPassword) throws TempPasswordDeletionException;
}
