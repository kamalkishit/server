package com.humanize.server.service;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class GoogleJsonParserServiceImpl implements JsonParserService {

    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        try {
            return new Gson().fromJson(json, classOfT);
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public String toJson(Object src) throws JsonIOException {
        try {
            return new Gson().toJson(src);
        } catch (JsonIOException exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
