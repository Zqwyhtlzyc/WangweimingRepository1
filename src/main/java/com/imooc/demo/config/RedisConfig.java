package com.imooc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

@Configuration
public class RedisConfig {

    @Bean
    public JedisPoolConfig getJedisPool(){
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setMaxIdle(6);
//        poolConfig.setMinIdle(0);
//        poolConfig.setMaxWaitMillis(-1);
//        poolConfig.setMaxTotal(6);

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMinIdle(10);
        config.setMaxWaitMillis(-1);
        config.setMaxIdle(20);
        return config;

    }

    @Bean
    public Jedis getJedisCluster(){
//       // HostAndPort hostAndPort = new HostAndPort("127.0.0.1",6379);
//
//        return new Jedis(hostAndPort,20000,3000,10,getJedisPool());

        JedisPool jedisPool = new JedisPool(getJedisPool(),"127.0.0.1",6379);
        return jedisPool.getResource();
    }


}
