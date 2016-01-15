package com.humanize.server.service;

import com.humanize.server.data.Content;
import com.humanize.server.exception.ImageDownloadException;

public interface ImageDownloaderService {
	
	boolean downloadImage(Content content) throws ImageDownloadException;
}
