package com.imooc.demo.server;

import com.imooc.demo.dao.RedisTokenDao;
import com.imooc.demo.dao.SysIsvConfigMapper;
import com.imooc.demo.dto.SysIsvConfig;
import com.imooc.demo.dto.SysIsvConfigExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysIsvConfigServerImpl implements SysIsvConfigServer {
    private static final Logger logger = LoggerFactory.getLogger(RedisTokenDao.class);


    @Autowired
    private RedisTokenDao redisTokenDao;
    @Resource
    private SysIsvConfigMapper sysIsvConfigMapper;
    @Override
    public String selectByPrimaryKey(Long id){
        String key = "key";
        try {
            redisTokenDao.setToken("toekntoken",key);
        } catch (Exception e) {
            logger.error("存入redis异常",e);
            e.printStackTrace();
        }
        Boolean boo = redisTokenDao.existToken(key);
        System.out.println("boo值："+boo);
        return redisTokenDao.getToken(key);
    }

    @Override
    public List<SysIsvConfig> selectByPrimaryKeytest(Long id){
        SysIsvConfigExample sysIsvConfigExample = new SysIsvConfigExample();
        SysIsvConfigExample.Criteria criteria = sysIsvConfigExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<SysIsvConfig> sysIsvConfigList = sysIsvConfigMapper.selectByExample(sysIsvConfigExample);
        sysIsvConfigList.stream().filter(sysIsvConfig -> {
            if("深物业".equals(sysIsvConfig.getUserName())){
               return true;
            }
            return false;
        }
        ).collect(Collectors.toList());

        return sysIsvConfigList;
    }
}
