package com.humanize.server.service;

import com.google.gson.Gson;

public class GoogleJsonParserImpl implements JsonParser{

    public <T> T fromJson(String json, Class<T> classOfT) throws Exception {
        try {
            return new Gson().fromJson(json, classOfT);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public String toJson(Object src) throws Exception {
        try {
            return new Gson().toJson(src);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
