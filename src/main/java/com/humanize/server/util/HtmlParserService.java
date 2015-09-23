package com.humanize.server.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.humanize.server.config.Config;
import com.humanize.server.data.Content;

@Service
public class HtmlParserService {

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
			content.setImageURL(document.select("meta[property=og:image]")
					.first().attr("content"));
			/*
			 * if (downloadImage(content.getImageURL(), content.getContentId()))
			 * { return content; } return null;
			 */
			return content;
		} catch (IOException ioException) {
			ioException.printStackTrace();
			return null;
		}
	}

	private boolean downloadImage(String imageURL, String destinationFilename) {
		try {
			URL url = new URL(imageURL);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("User-Agent", "Mozilla");
			String imageFilename = Config.IMAGE_FOLDER + destinationFilename
					+ imageURL.substring(imageURL.lastIndexOf('.'));
			System.out.println(imageFilename);
			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					imageFilename));

			for (int i; (i = in.read()) != -1;) {
				out.write(i);
			}
			in.close();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Content parse(Content content) {
		return parseURL(content);
	}

	public ArrayList<Content> parse(ArrayList<Content> contents) {
		ArrayList<Content> returnArray = new ArrayList<Content>();
		for (Content content : contents) {
			Content tempContent = parse(content);
			returnArray.add(tempContent);
		}

		return returnArray;
	}
}
