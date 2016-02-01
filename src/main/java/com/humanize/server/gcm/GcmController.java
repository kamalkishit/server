package com.humanize.server.gcm;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.humanize.server.exception.UserDeviceCreationException;
import com.humanize.server.exception.UserDeviceUpdateException;

@RestController
public class GcmController {
	
	@Autowired
	GcmService gcmService;
	
	final String GOOGLE_API_KEY ="AIzaSyDZ-EElySkCcWRvVT9R27fzhc0uywwx4Tw";
	
	@RequestMapping("/api/gcm/push")
	public ResponseEntity<Boolean> post(String text) throws Exception, IOException {
		UserDetail user = new UserDetail();
		user.setRegId(text);
		final String regId = user.getRegId();
		
		new Thread(){
			public void run(){
				try {
					Sender sender = new Sender(GOOGLE_API_KEY);
					Message message = new Message.Builder()
					.collapseKey("message")
					.timeToLive(3)
					.delayWhileIdle(true)
					.addData("message", "Welcome to your Push Notifications")
					.build();
					
					Result result = sender.send(message, regId, 1);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}.start();
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@RequestMapping("/api/gcm/register")
	public ResponseEntity<Boolean> register(@RequestBody UserDevice userDevice) throws UserDeviceCreationException, UserDeviceUpdateException {
		return new ResponseEntity<Boolean>(gcmService.registerDevice(userDevice), HttpStatus.OK);
	}
}
