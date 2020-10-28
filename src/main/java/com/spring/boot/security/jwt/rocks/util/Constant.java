package com.spring.boot.security.jwt.rocks.util;

public class Constant {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "yoursecretkey";
    public static final String AUTHORITIES_KEY = "scopes";
}
