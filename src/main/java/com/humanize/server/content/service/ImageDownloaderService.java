package com.humanize.server.content.service;

import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import com.humanize.server.config.Config;
import com.humanize.server.content.data.Content;

@Service
public class ImageDownloaderService {
	
	private URL url;
	private URLConnection urlConnection;
	
	public boolean downloadImage(Content content) {
		createConnection(content);
		String tempImageFilename = getTempImageFilename(content);
		String imageFilename = getImageFilename(content);
		readImage(tempImageFilename);
		BufferedImage bufferedImage = processImage(tempImageFilename);
		saveImage(bufferedImage, getExtension(content), imageFilename);
		content.setImageURL(content.getContentId() + "." + getExtension(content));
		
		return true;
	}
	
	private void createConnection(Content content) {
		try {
			url = new URL(content.getOriginalImageURL());
			urlConnection = url.openConnection();
			urlConnection.setRequestProperty("User-Agent", "Mozilla");
		} catch (MalformedURLException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	private String getTempImageFilename(Content content) {
		String tempImageFilename = Config.TEMP_FOLDER + content.getContentId() + content.getOriginalImageURL().substring(content.getOriginalImageURL().lastIndexOf('.'));
		
		return tempImageFilename;
	}
	
	private String getImageFilename(Content content) {
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
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
	
	private BufferedImage processImage(String tempImageFilename) {
		File inputFile = null;
		BufferedImage inputImage = null;
		BufferedImage outputImage = null;
		
		try {
			inputFile = new File(tempImageFilename);
			inputImage = ImageIO.read(inputFile);
			outputImage = Scalr.resize(inputImage,
					Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, 512, 288,
					Scalr.OP_ANTIALIAS);
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
		} catch (ImagingOpException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
		return outputImage;
	}
	
	private String getExtension(Content content) {
		String extension = content.getOriginalImageURL().substring(
				content.getOriginalImageURL().lastIndexOf('.') + 1);
		
		return extension;
	}
	
	private void saveImage(BufferedImage bufferedImage, String extension, String imageFilename) {
		try {
			ImageIO.write(bufferedImage, extension, new File(imageFilename));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
