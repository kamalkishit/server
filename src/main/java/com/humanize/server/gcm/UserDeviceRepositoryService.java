package com.humanize.server.gcm;

import com.humanize.server.data.User;
import com.humanize.server.exception.UserDeviceCreationException;
import com.humanize.server.exception.UserDeviceDeletionException;
import com.humanize.server.exception.UserDeviceNotFoundException;
import com.humanize.server.exception.UserDeviceUpdateException;

public interface UserDeviceRepositoryService {

	UserDevice create(UserDevice userDevice) throws UserDeviceCreationException;
	UserDevice update(UserDevice userDevice) throws UserDeviceUpdateException;
	UserDevice findByDeviceId(String deviceId) throws UserDeviceNotFoundException;
	void deleteByDeviceId(String deviceId) throws UserDeviceDeletionException;
	void delete(UserDevice userDevice) throws UserDeviceDeletionException;
}
