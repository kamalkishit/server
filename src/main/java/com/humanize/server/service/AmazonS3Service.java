package com.humanize.server.service;

import java.io.InputStream;



import com.humanize.server.content.data.Content;
import com.humanize.server.exception.AmazonS3ImageCreationException;
import com.humanize.server.exception.AmazonS3ImageNotFoundException;

public interface AmazonS3Service {

	public void putImage(Content content) throws AmazonS3ImageCreationException;
	public void getImage(Content content) throws AmazonS3ImageNotFoundException;
	public InputStream getImage(String imageName) throws AmazonS3ImageNotFoundException;
}
