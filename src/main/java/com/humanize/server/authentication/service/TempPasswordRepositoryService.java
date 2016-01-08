package com.humanize.server.authentication.service;

import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.exception.TempPasswordCreationException;
import com.humanize.server.authentication.exception.TempPasswordDeletionException;
import com.humanize.server.authentication.exception.TempPasswordNotFoundException;
import com.humanize.server.authentication.exception.TempPasswordUpdateException;

public interface TempPasswordRepositoryService {

	TempPassword create(TempPassword tempPassword) throws TempPasswordCreationException;
	TempPassword createOrUpdate(TempPassword tempPassword) throws TempPasswordCreationException, TempPasswordUpdateException;
	TempPassword update(TempPassword tempPassword) throws TempPasswordUpdateException;
	TempPassword findByEmailId(String emailId) throws TempPasswordNotFoundException;
	void delete(String emailId) throws TempPasswordDeletionException;
	void delete(TempPassword tempPassword) throws TempPasswordDeletionException;
}
