package com.imooc.demo.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisTokenDao {
    private static final Logger logger = LoggerFactory.getLogger(RedisTokenDao.class);

    @Resource
    private Jedis jedisCluster;

    private final static String KEY = "DEMO";
    private final static Long TOKEN_EXPIRE = TimeUnit.MINUTES.toMinutes(10);//十分钟过期
    private final static String RESULT_OK = "OK";
    private final static Long RESULT_1 = 1L;
    public Boolean setToken(String token,String... key){
        String tokenKey = buildTokenKey(key);
        String result = jedisCluster.set(tokenKey,token,"nx","ex",TOKEN_EXPIRE);
        logger.info("token写入redis结果：",result);
        return RESULT_OK.equals(result);
    }

    public Boolean setValue(String redisKey,String value){
        //String tokenKey = buildTokenKey(key);
        String result = jedisCluster.set(redisKey,value,"nx","ex",TOKEN_EXPIRE);
        logger.info("value写入redis结果：",result);
        return RESULT_OK.equals(result);
    }

    public Boolean hSetToken(String token,String field,String value){
        String tokenKey = buildTokenKey(token);
        Long result = jedisCluster.hset(tokenKey,field,value);
        logger.info("token写入redis结果：",result);
        return RESULT_1.equals(result);
    }

    public Map<String,String> hgetToken(String redisKey){
        String tokenKey = buildTokenKey(redisKey);
        Map<String,String> map = jedisCluster.hgetAll(tokenKey);
        logger.info("redisKey查询redis结果：",map);
        return map;
    }

    public String hgetToken(String redisKey,String field){
        String tokenKey = buildTokenKey(redisKey);
        String str = jedisCluster.hget(tokenKey,field);
        logger.info("redisKey和fileId查询redis结果：",str);
        return str;
    }


    public Boolean existToken(String... key){
        String tokenKey = buildTokenKey(key);
        return jedisCluster.exists(tokenKey);
    }
    public String getToken(String... key){
        String tokenKey = buildTokenKey(key);
        return jedisCluster.get(tokenKey);
    }

    public Boolean deferToken(String... key){
        String tokenKey = buildTokenKey(key);
        return jedisCluster.pexpire(tokenKey,TOKEN_EXPIRE*1000) == 1L;
    }

    private String buildTokenKey(String... key){
        StringBuffer tokenKey = new StringBuffer(KEY);
        for (int i=0; i<key.length-1;i++){
            tokenKey.append(key[i]).append("|");
        }
        tokenKey.append(key[key.length - 1]);
        return tokenKey.toString();
    }
}
