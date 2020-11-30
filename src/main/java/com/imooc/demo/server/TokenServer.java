package com.imooc.demo.server;

import java.io.UnsupportedEncodingException;

public interface TokenServer {
    String createToken(String userNo,String phone)throws UnsupportedEncodingException;

    boolean verifyToken(String token);

    boolean deferToken(String token);
}
