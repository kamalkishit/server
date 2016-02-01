package com.humanize.server.gcm;

import com.humanize.server.exception.UserDeviceCreationException;
import com.humanize.server.exception.UserDeviceUpdateException;

public interface GcmService {

	boolean registerDevice(UserDevice userDevice) throws UserDeviceCreationException, UserDeviceUpdateException;
}
