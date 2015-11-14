package com.humanize.server.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.humanize.server.config.Config;
import com.humanize.server.content.data.Content;

@Service
public class HtmlParserService {
/*
	static Logger logger = Logger.getLogger(HtmlParserService.class);

	private Content parseURL(Content content) {
		try {
			Document document = Jsoup.connect(content.getContentURL())
					.userAgent("Mozilla").get();

			System.out.println(content.getContentId());

			content.setContentId(UUID.randomUUID().toString());
			content.setTitle(document.select("meta[property=og:title]").first()
					.attr("content"));
			content.setDescription(document
					.select("meta[property=og:description]").first()
					.attr("content"));
			content.setSource(document.select("meta[property=og:site_name]")
					.first().attr("content"));
			content.setOriginalImageURL(document
					.select("meta[property=og:image]").first().attr("content"));

			// String url = document
			// .select("a[href~=https://www.facebook.com/* /videos*")
			// .select("a[href=https://www.facebook.com/ajplusenglish/videos/622919697849541/")
			// .first().attr("href").toString();

			if (downloadImage(content)) {
				return content;
			}

			return null;
			// return content;
		} catch (IOException ioException) {
			logger.error("parseURL", ioException);
			System.out.println("exception in main");
			System.out.println(content.getContentURL());
			ioException.printStackTrace();
			return null;
		}
	}

	private boolean downloadImage(Content content) {
		try {
			URL url = new URL(content.getOriginalImageURL());
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("User-Agent", "Mozilla");
			String tempImageFilename = Config.TEMP_FOLDER
					+ content.getOriginalImageURL().substring(
							content.getOriginalImageURL().lastIndexOf('.'));
			String imageFilename = Config.IMAGE_FOLDER
					+ content.getContentId()
					+ content.getOriginalImageURL().substring(
							content.getOriginalImageURL().lastIndexOf('.'));
			System.out.println(content.getContentURL());
			System.out.println(content.getOriginalImageURL());
			System.out.println(imageFilename);
			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					tempImageFilename));

			for (int i; (i = in.read()) != -1;) {
				out.write(i);
			}

			in.close();
			out.close();

			File inputFile = new File(tempImageFilename);
			BufferedImage inputImage = ImageIO.read(inputFile);
			BufferedImage outputImage = Scalr.resize(inputImage,
					Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, 512, 288,
					Scalr.OP_ANTIALIAS);
			// ImageIO.read(inputFile);

			// creates output image
			/*
			 * BufferedImage outputImage = new BufferedImage(512, 384,
			 * inputImage.getType());
			 * 
			 * // scales the input image to the output image Graphics2D g2d =
			 * outputImage.createGraphics(); g2d.drawImage(inputImage, 0, 0,
			 * 512, 384, null); g2d.dispose();
			 * 
			 * // extracts extension of output file
			 */ 
	/*
			String formatName = content.getOriginalImageURL().substring(
					content.getOriginalImageURL().lastIndexOf('.') + 1);

			// System.out.println(imageFilename);
			// System.out.println(formatName);

			// writes to output file
			ImageIO.write(outputImage, formatName, new File(imageFilename));
			content.setImageURL(content.getContentId() + "." + formatName);

			return true;
		} catch (MalformedURLException e) {
			
		}
		catch (IOException e) {
			logger.error("downloadImage", e);
			System.out.println("exception in download");
			System.out.println(content.getImageURL());
			e.printStackTrace();
			return false;
		}
	}

	public Content parse(Content content) {
		return parseURL(content);
	}

	public Content parse(String url) {
		Content content = new Content();
		content.setContentURL(url);
		return parse(content);
	}

	public ArrayList<Content> parse(ArrayList<Content> contents) {
		ArrayList<Content> returnArray = new ArrayList<Content>();
		for (Content content : contents) {
			Content tempContent = parse(content);
			if (tempContent != null) {
				returnArray.add(tempContent);
			}

		}

		return returnArray;
	} */
}
