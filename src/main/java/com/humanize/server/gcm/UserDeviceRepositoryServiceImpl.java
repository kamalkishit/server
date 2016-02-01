package com.humanize.server.gcm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.exception.ErrorCodes;
import com.humanize.server.exception.UserDeviceCreationException;
import com.humanize.server.exception.UserDeviceDeletionException;
import com.humanize.server.exception.UserDeviceNotFoundException;
import com.humanize.server.exception.UserDeviceUpdateException;

@Service
public class UserDeviceRepositoryServiceImpl implements UserDeviceRepositoryService {

	@Autowired
	private UserDeviceRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDeviceRepositoryServiceImpl.class);
	private static final String TAG = UserDeviceRepositoryServiceImpl.class.getSimpleName();
	
	public UserDevice create(UserDevice userDevice) throws UserDeviceCreationException {
		userDevice = repository.save(userDevice);
		
		if (userDevice != null) {
			return userDevice;
		}
		
		throw new UserDeviceCreationException(ErrorCodes.USER_DEVICE_CREATION_ERROR);
	}
	
	public UserDevice update(UserDevice userDevice) throws UserDeviceUpdateException {
		try {
			findByDeviceId(userDevice.getDeviceId());
			
			userDevice = repository.save(userDevice);
			
			if (userDevice != null) {
				return userDevice;
			}
			
			throw new UserDeviceUpdateException(ErrorCodes.USER_DEVICE_UPDATE_ERROR);
		} catch (UserDeviceNotFoundException exception) {
			logger.error(TAG, exception);
			throw new UserDeviceUpdateException(ErrorCodes.USER_DEVICE_UPDATE_ERROR);
		}
	}
	
	public UserDevice findByDeviceId(String deviceId) throws UserDeviceNotFoundException {
		UserDevice userDevice = repository.findByDeviceId(deviceId);
		
		if (userDevice != null) {
			return userDevice;
		}
		
		throw new UserDeviceNotFoundException(ErrorCodes.USER_DEVICE_NOT_FOUND_ERROR);
	}
	
	public void deleteByDeviceId(String deviceId) throws UserDeviceDeletionException {
		try {
			UserDevice userDevice = findByDeviceId(deviceId);
			repository.delete(userDevice);
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new UserDeviceDeletionException(ErrorCodes.USER_DEVICE_DELETION_ERROR);
		}	
	}
	
	public void delete(UserDevice userDevice) throws UserDeviceDeletionException{
		repository.delete(userDevice);
	}
}

