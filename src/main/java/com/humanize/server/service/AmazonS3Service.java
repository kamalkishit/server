
package com.humanize.server.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.content.data.Content;
import com.humanize.server.exception.AmazonS3CreationException;

@Service
public class AmazonS3Service {
	
	private AmazonS3 s3Client;
	private String bucketName;

	public AmazonS3Service() {
		s3Client = new AmazonS3Client(new BasicAWSCredentials());
		bucketName = "com-humanize-images";
	}
	
	public void putImage(Content content) {
		try {
			String key = "null.jpg";
			File file = new File("/root/images/null.jpg");
			s3Client.putObject(new PutObjectRequest(bucketName, key, file));
		} catch (AmazonServiceException exception) {
			exception.printStackTrace();
			throw new AmazonS3CreationException(ExceptionConfig.AMAZON_S3_CREATION_ERROR_CODE, ExceptionConfig.AMAZON_S3_CREATION_EXCEPTION);
		} catch (AmazonClientException exception) {
			exception.printStackTrace();
			throw new AmazonS3CreationException(ExceptionConfig.AMAZON_S3_CREATION_ERROR_CODE, ExceptionConfig.AMAZON_S3_CREATION_EXCEPTION);
		}
	}
}
