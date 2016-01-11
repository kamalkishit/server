package com.humanize.server.service;

public interface JsonParser {

    <T> T fromJson(String json, Class<T> classOfT) throws Exception;
    String toJson(Object src) throws Exception;
}
