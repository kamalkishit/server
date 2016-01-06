package com.humanize.server.service;

import java.io.File;
import java.io.InputStream;

import com.humanize.server.exception.ImageNotFoundException;

public interface ImageService {
	
	InputStream getImage(String imageName) throws ImageNotFoundException;
	File get(String imageName);
}
