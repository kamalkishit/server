package com.humanize.server.helper;

import java.net.InetAddress;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InternetHelper {
	
	@Autowired
	RandomNumberGenerator randomNumberGenerator;
	
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
			return null;
		}	
	}
}
