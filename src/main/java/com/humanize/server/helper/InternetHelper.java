package com.humanize.server.helper;

import java.net.InetAddress;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InternetHelper {
	
	@Autowired
	RandomNumberGenerator randomNumberGenerator;
	
	private static final Logger logger = LoggerFactory.getLogger(InternetHelper.class);
	private static final String TAG = InternetHelper.class.getSimpleName();
	
	public int getIpAddressIntValue() {
		String string = getIpAddress();
		
		if (string == null) {
			return randomNumberGenerator.randInt(0, 100);
		} else {
			String delims = ".";
			StringTokenizer stringTokenizer = new StringTokenizer(string, delims);
			String endString = "";
			while(stringTokenizer.hasMoreElements()) {
				endString = (String) stringTokenizer.nextElement();		
			}
			
			return Integer.parseInt(endString);
		}
	}
	
	private String getIpAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch(Exception exception) {
			logger.error(TAG, exception);
			return null;
		}	
	}
}
