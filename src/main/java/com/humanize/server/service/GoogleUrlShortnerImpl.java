package com.humanize.server.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.humanize.server.data.GoogleUrlShortnerResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class GoogleUrlShortnerImpl implements UrlShortner {

	public String getShortUrl(String longUrl) {
		try {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			OkHttpClient client = new OkHttpClient();
			
			String json = "{\"longUrl\": \"" + longUrl + "\"}";
			System.out.println(json);
			
			RequestBody requestBody = RequestBody.create(JSON, json);
			
			Request request = new Request.Builder().url("https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyCQiHgcWArX0lfhGSxqwGepDpc1W9eJEIc").post(requestBody).build();

		    Response response = client.newCall(request).execute();
		    if (!response.isSuccessful()) {
		    	throw new IOException("Unexpected code " + response);
		    }
		    
		    GoogleUrlShortnerResponse response2 = new GoogleJsonParserImpl().fromJson(response.body().string().toString(), GoogleUrlShortnerResponse.class);
		    return response2.getId();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return null;
	}
}
