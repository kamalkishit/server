package com.humanize.server.service;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.humanize.server.config.Config;
import com.humanize.server.data.Content;
import com.humanize.server.exception.ErrorCodes;
import com.humanize.server.exception.S3ImageCreationException;
import com.humanize.server.exception.S3ImageNotFoundException;

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {
	
	private AmazonS3 s3Client;
	
	private static final Logger logger = LoggerFactory.getLogger(AmazonS3ServiceImpl.class);
	private static final String TAG = AmazonS3ServiceImpl.class.getSimpleName();

	public AmazonS3ServiceImpl() {
		s3Client = new AmazonS3Client(new BasicAWSCredentials("AKIAILGFWMFKMZVXRGIQ", "mcftDmizMAGfPL9vdEmP7G9Zl1wBvAnCcPJGpFmu"));
	}
	
	public void putImage(Content content) throws S3ImageCreationException {
		try {
			String key = content.getImageId();
			File file = new File(Config.IMAGE_FOLDER + key);
			s3Client.putObject(new PutObjectRequest(Config.S3_BUCKET_IMAGES, key, file));
		} catch (AmazonServiceException exception) {
			logger.error(TAG, exception);
			throw new S3ImageCreationException(ErrorCodes.S3_IMAGE_CREATION_ERROR);
		} catch (AmazonClientException exception) {
			logger.error(TAG, exception);
			throw new S3ImageCreationException(ErrorCodes.S3_IMAGE_CREATION_ERROR);
		}
	}
	
	public void getImage(Content content) throws S3ImageNotFoundException {
		try {
			S3Object s3Object = s3Client.getObject(Config.S3_BUCKET_IMAGES, content.getImageURL());
		} catch (AmazonServiceException exception) {
			logger.error(TAG, exception);
			throw new S3ImageNotFoundException(ErrorCodes.S3_IMAGE_NOT_FOUND_ERROR);
		} catch (AmazonClientException exception) {
			logger.error(TAG, exception);
			throw new S3ImageNotFoundException(ErrorCodes.S3_IMAGE_NOT_FOUND_ERROR);
		}
	}
	
	public InputStream getImage(String imageName) throws S3ImageNotFoundException {
		S3Object s3Object = null;
		
		try {
			s3Object = s3Client.getObject(Config.S3_BUCKET_IMAGES, imageName);
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new S3ImageNotFoundException(ErrorCodes.S3_IMAGE_NOT_FOUND_ERROR);
		}
		
		return s3Object.getObjectContent();
	}
}
