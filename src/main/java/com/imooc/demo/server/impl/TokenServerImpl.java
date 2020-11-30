package com.imooc.demo.server.impl;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.imooc.demo.dao.RedisTokenDao;
import com.imooc.demo.server.TokenServer;
import com.imooc.demo.utils.Libs;
import com.imooc.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class TokenServerImpl implements TokenServer {

    @Autowired
    private RedisTokenDao redisTokenDao;

    private static final String FIELD_USERNO = "userNo";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_KEY = "token-key";
    @Override
    public String createToken(String userNo,String phone) throws UnsupportedEncodingException {
       //如果redis中存在相同的key
        if(redisTokenDao.existToken(userNo,phone)){
            return redisTokenDao.getToken(userNo,phone);
        }


        String tokenKey = UUID.randomUUID().toString();
        Map<String,String> map = new HashMap<>();
        map.put(FIELD_USERNO,userNo);
        map.put(FIELD_PHONE,phone);
        map.put(FIELD_KEY,tokenKey);

        String token = TokenUtils.creaToken(map);

        //将生成的token存入redis
        redisTokenDao.setToken(token,userNo,phone);

        return token;
    }


    @Override
    public boolean verifyToken(String token) {
        DecodedJWT decodedToken = TokenUtils.decodedToken(token);
        if (decodedToken == null) {
            return false;
        }
        // 通过解析传入的token，从redis中获取相应的token对比
        String[] strings = getRedisKeyFromClaims(decodedToken.getClaims());
        String tokenRedis = redisTokenDao.getToken(strings);
        if (null !=tokenRedis &&!("invalid").equals(tokenRedis)) {
            // redis中token存在，判断是否相等
           return tokenRedis.equals(token);
        }else {
            return false;
        }
    }

    @Override
    public boolean deferToken(String token) {
        DecodedJWT decodedToken = TokenUtils.decodedToken(token);
        if (decodedToken == null) {
            return false;
        }
        String[] strings = getRedisKeyFromClaims(decodedToken.getClaims());
        String tokenRedis = redisTokenDao.getToken(strings);
        if (!Libs.isNull(tokenRedis)){
            return redisTokenDao.deferToken(strings) ;
        }
        return false;
    }


    private String[] getRedisKeyFromClaims(Map<String, Claim> map) {
        String[] array = new String[1];
        array[0] = map.get(FIELD_USERNO).asString();
        array[1] = map.get(FIELD_PHONE).asString();
        array[2] = map.get(FIELD_KEY).asString();
//        if (Libs.isNull(map.get("usId"))){
//            array[0]="";
//        }else {
//            array[0]= map.get("usId").asString();
//        }
        return array;
    }

}
