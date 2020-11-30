package com.imooc.demo.testMain;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class test {

    public static void main(String[] args) throws Exception{
//        for (int i = 0; i < 100; i++) {
//            System.out.println(new Date());
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }



        // 添加集群的服务节点Set集合
        Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
        // 添加节点
        hostAndPortsSet.add(new HostAndPort("99.11.54.41", 6379));
//        hostAndPortsSet.add(new HostAndPort("127.0.0.1", 6379));
//        hostAndPortsSet.add(new HostAndPort("127.0.0.1", 6379));

        HostAndPort hostAndPort = new HostAndPort("99.11.54.41",6379);
        JedisCluster jedisCluster=new JedisCluster(hostAndPort,20000,3000,10,getJedisPool());

        System.out.println("是否OK："+jedisCluster.set("tokenKey","token","nx","ex", TimeUnit.MINUTES.toMinutes(10)));
    }

    public static GenericObjectPoolConfig getJedisPool(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(6);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWaitMillis(-1);
        poolConfig.setMaxTotal(6);

        return poolConfig;

    }

}
