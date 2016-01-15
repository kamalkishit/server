package com.humanize.server.service;

public interface SaltedHashPasswordService {

    String getSaltedHash(String password) throws Exception;
    boolean check(String password, String hashedPassword) throws Exception;
}
