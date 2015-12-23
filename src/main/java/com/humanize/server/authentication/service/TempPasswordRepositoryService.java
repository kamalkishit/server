package com.humanize.server.authentication.service;

import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.exception.TempPasswordCreationException;
import com.humanize.server.authentication.exception.TempPasswordDeletionException;
import com.humanize.server.authentication.exception.TempPasswordNotFoundException;
import com.humanize.server.authentication.exception.TempPasswordUpdationException;

public interface TempPasswordRepositoryService {

	public TempPassword create(TempPassword tempPassword) throws TempPasswordCreationException;
	public TempPassword createOrUpdate(TempPassword tempPassword) throws TempPasswordCreationException, TempPasswordUpdationException;
	public TempPassword update(TempPassword tempPassword) throws TempPasswordUpdationException;
	public TempPassword findByEmailId(String emailId) throws TempPasswordNotFoundException;
	public void delete(String emailId) throws TempPasswordDeletionException;
	public void delete(TempPassword tempPassword);
}
