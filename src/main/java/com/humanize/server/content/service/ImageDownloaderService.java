package com.humanize.server.content.service;

import com.humanize.server.content.data.Content;
import com.humanize.server.content.exception.ImageDownloadException;

public interface ImageDownloaderService {
	
	boolean downloadImage(Content content) throws ImageDownloadException;
}
