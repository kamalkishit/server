package com.humanize.server.service;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public interface JsonParserService {

    <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException;
    String toJson(Object src) throws JsonIOException;
}
