package com.humanize.server.service;

import java.io.InputStream;

public interface ImageService {
	
	public InputStream getImage(String imageName) throws Exception;
}
