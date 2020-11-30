package com.imooc.demo.controller;

import com.imooc.demo.config.LogAround;
import com.imooc.demo.dao.RedisTokenDao;
import com.imooc.demo.dao.SysIsvConfigMapper;
import com.imooc.demo.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/test")
public class testController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysIsvConfigMapper sysIsvConfigMapper;
    @Resource
    private RedisTokenDao redisTokenDao;

    @PostMapping("/queryInfo")
    public Result query(@RequestBody @Valid Request<SysIsvConfigDto> request){
       logger.info("收到数据查询的请求："+request);
       Boolean boo = redisTokenDao.setToken("aa","AA");
        System.out.println("数据存储成功:"+boo);
        Map map = new HashMap();
       map.put("SUC0000","成功");
       map.put("FAIL","失败");
       return Result.okCode("SUC0000","查询成功",map);
    }

    @PostMapping("/testSql")
    @LogAround(description = "收到testSql的请求")
    public Result testSql(@RequestBody @Valid Request<SysIsvConfigDto> request){

        Map hashTable = new Hashtable();
        Map hashMap = new HashMap();
        Map concurrentHashMap = new ConcurrentHashMap();
        hashMap.put(null,"");
        hashTable.put(null,"");
        concurrentHashMap.put(null,"");
        //排序  按照id
        List<SysIsvConfig> ecnSysKeyList = new ArrayList<>();
        //ecnSysKeyList.sort(ecnSysKeyList.get(0));
        ecnSysKeyList.sort(Comparator.comparing(SysIsvConfig::getId));
        ecnSysKeyList.forEach(ecn -> System.out.println(ecn));
        ecnSysKeyList.forEach(System.out::println);

        //logger.info("收到testSql的请求：");
        SysIsvConfigExample sysIsvConfigExample = new SysIsvConfigExample();
        SysIsvConfigExample.Criteria criteria = sysIsvConfigExample.createCriteria();
//        criteria.andCreateTimeEqualTo("")
//                .andDesKeyEqualTo("");
        List<SysIsvConfig> sysIsvConfigList =  sysIsvConfigMapper.selectByExample(sysIsvConfigExample);
        List<SysIsvConfig> sysIsvConfigList1 =  sysIsvConfigList.stream().filter(sysIsvConfig -> {
                    if("深物业".equals(sysIsvConfig.getUserName())){
                        return true;
                    }
                    return false;
                }
        ).collect(Collectors.toList());

        System.out.println("集合大小："+sysIsvConfigList1.size());
        return Result.ok("查询成功");
    }

}
