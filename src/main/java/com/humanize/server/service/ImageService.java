package com.humanize.server.service;

import java.io.InputStream;

import com.humanize.server.exception.ImageNotFoundException;

public interface ImageService {
	
	public InputStream getImage(String imageName) throws ImageNotFoundException;
}
