package com.imooc.demo.server;

import com.imooc.demo.dto.SysIsvConfig;

import java.util.List;

public interface SysIsvConfigServer {

    String selectByPrimaryKey(Long id);
    List<SysIsvConfig> selectByPrimaryKeytest(Long id);
}
