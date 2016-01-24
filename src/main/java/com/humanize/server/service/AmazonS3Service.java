package com.humanize.server.service;

import java.io.InputStream;

import com.humanize.server.data.Content;
import com.humanize.server.exception.S3ImageCreationException;
import com.humanize.server.exception.S3ImageNotFoundException;

public interface AmazonS3Service {

	void putImage(Content content) throws S3ImageCreationException;
	void getImage(Content content) throws S3ImageNotFoundException;
	InputStream getImage(String imageName) throws S3ImageNotFoundException;
}
