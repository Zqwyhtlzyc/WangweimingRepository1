package com.imooc.demo;

import com.imooc.demo.dao.SysIsvConfigMapper;
import com.imooc.demo.dto.SysIsvConfig;
import com.imooc.demo.dto.SysIsvConfigExample;
import com.imooc.demo.server.SysIsvConfigServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Resource
    private SysIsvConfigServer sysIsvConfigServer;
    @Resource
    private SysIsvConfigMapper sysIsvConfigMapper;
    @Test
    public void contextLoads() {
        SysIsvConfigExample sysIsvConfigExample = new SysIsvConfigExample();
        SysIsvConfigExample.Criteria criteria = sysIsvConfigExample.createCriteria();
        List<SysIsvConfig> sysIsvConfigList =  sysIsvConfigMapper.selectByExample(sysIsvConfigExample);
        List<SysIsvConfig> sysIsvConfigList1 =  sysIsvConfigList.stream().filter(sysIsvConfig -> {
                    if("深物业".equals(sysIsvConfig.getUserName())){
                        return true;
                    }
                    return false;
                }
        ).collect(Collectors.toList());

        System.out.println("集合大小："+sysIsvConfigList1.size());
    }

    @Test
    public void jedisTest(){
        Jedis jedis =new Jedis("127.0.0.1",6379);

        jedis.set("name","wangwm");
        String name = jedis.get("name");
        System.out.println("名字："+name);
    }
}
