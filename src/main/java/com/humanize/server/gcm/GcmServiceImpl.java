package com.humanize.server.gcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.exception.UserDeviceCreationException;
import com.humanize.server.exception.UserDeviceNotFoundException;
import com.humanize.server.exception.UserDeviceUpdateException;

@Service
public class GcmServiceImpl implements GcmService {
	
	@Autowired
	UserDeviceRepositoryService repositoryService;

	public boolean registerDevice(UserDevice userDevice) throws UserDeviceCreationException, UserDeviceUpdateException {
		try {
			UserDevice userDeviceObj = repositoryService.findByDeviceId(userDevice.getDeviceId());
			userDeviceObj.setGcmRegistrationToken(userDevice.getGcmRegistrationToken());
			repositoryService.update(userDeviceObj);
			return true;
		} catch (UserDeviceNotFoundException exception) {
			repositoryService.create(userDevice);
			return false;
		}
	}
}
