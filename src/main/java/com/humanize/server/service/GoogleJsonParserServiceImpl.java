package com.humanize.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class GoogleJsonParserServiceImpl implements JsonParserService {
	
	private static final Logger logger = LoggerFactory.getLogger(GoogleJsonParserServiceImpl.class);
	private static final String TAG = GoogleJsonParserServiceImpl.class.getSimpleName();

    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        try {
            return new Gson().fromJson(json, classOfT);
        } catch (JsonSyntaxException exception) {
        	logger.error(TAG, exception);
            throw exception;
        }
    }

    public String toJson(Object src) throws JsonIOException {
        try {
            return new Gson().toJson(src);
        } catch (JsonIOException exception) {
        	logger.error(TAG, exception);
            throw exception;
        }
    }
}
