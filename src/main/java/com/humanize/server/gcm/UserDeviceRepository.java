package com.humanize.server.gcm;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.data.User;

public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {

	UserDevice findByDeviceId(String deviceId);
}
