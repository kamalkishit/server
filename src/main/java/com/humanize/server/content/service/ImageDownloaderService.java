package com.humanize.server.content.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;

import com.humanize.server.config.Config;
import com.humanize.server.content.data.Content;

@Service
public class ImageDownloaderService {
	
	private URL url;
	private URLConnection urlConnection;
	
	public boolean downloadImage(Content content) {
		createConnection(content);
		String tempImageFilename = createTempImageFilename(content);
		String imageFilename = createImageFilename(content);


		
		return true;
		
	}
	
	private void createConnection(Content content) {
		try {
			url = new URL(content.getOriginalImageURL());
			urlConnection = url.openConnection();
		} catch (MalformedURLException exception) {
			
		} catch (IOException exception) {
			
		}
	}
	
	private String createTempImageFilename(Content content) {
		String tempImageFilename = Config.TEMP_FOLDER + content.getOriginalImageURL().substring(content.getOriginalImageURL().lastIndexOf('.'));
		
		return tempImageFilename;
	}
	
	private String createImageFilename(Content content) {
		String imageFilename = Config.IMAGE_FOLDER + content.getContentId() + content.getOriginalImageURL().substring(content.getOriginalImageURL().lastIndexOf('.'));
		
		return imageFilename;
	}
	
	private void readImage(String tempImageFilename) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			inputStream = new BufferedInputStream(
					urlConnection.getInputStream());
			outputStream = new BufferedOutputStream(new FileOutputStream(
					tempImageFilename));

			for (int i; (i = inputStream.read()) != -1;) {
				outputStream.write(i);
			}
		} catch (FileNotFoundException exception) {
			
		} catch (IOException exception) {
			
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException exception) {
				
			}
			
		}
	}
}
