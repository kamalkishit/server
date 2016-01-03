package com.humanize.server.service;

import java.io.InputStream;



import com.humanize.server.content.data.Content;
import com.humanize.server.exception.S3ImageCreationException;
import com.humanize.server.exception.S3ImageNotFoundException;

public interface AmazonS3Service {

	public void putImage(Content content) throws S3ImageCreationException;
	public void getImage(Content content) throws S3ImageNotFoundException;
	public InputStream getImage(String imageName) throws S3ImageNotFoundException;
}
