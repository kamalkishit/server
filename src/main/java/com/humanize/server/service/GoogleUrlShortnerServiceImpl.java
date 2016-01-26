package com.humanize.server.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.humanize.server.config.Config;
import com.humanize.server.data.GoogleUrlShortnerResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class GoogleUrlShortnerServiceImpl implements UrlShortnerService {
	
	private static final Logger logger = LoggerFactory.getLogger(GoogleUrlShortnerServiceImpl.class);
	private static final String TAG = GoogleUrlShortnerServiceImpl.class.getSimpleName();

	public String getShortUrl(String longUrl) {
		try {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			OkHttpClient client = new OkHttpClient();
			
			RequestBody requestBody = RequestBody.create(JSON, createJson(longUrl));
			
			Request request = new Request.Builder().url(createUrlShortnerUrl()).post(requestBody).build();
			//"https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyCQiHgcWArX0lfhGSxqwGepDpc1W9eJEIc"
			
		    Response response = client.newCall(request).execute();
		    if (!response.isSuccessful()) {
		    	throw new IOException("Unexpected code " + response);
		    }
		    
		    GoogleUrlShortnerResponse response2 = new GoogleJsonParserServiceImpl().fromJson(response.body().string().toString(), GoogleUrlShortnerResponse.class);
		    return response2.getId();
		} catch (Exception exception) {
			logger.error(TAG, exception);
			exception.printStackTrace();
		}
		
		return null;
	}
	
	private String createUrlShortnerUrl() {
		return Config.GOOGLE_URL_SHORNER_API_URL + "?key=" + Config.GOOLE_URL_SHORTNER_API_KEY;
	}
	private String createJson(String longUrl) {
		return "{\"longUrl\": \"" + longUrl + "\"}";
	}
}
