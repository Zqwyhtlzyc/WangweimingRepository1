package com.imooc.demo.dao;

import com.imooc.demo.dto.SysIsvConfig;
import com.imooc.demo.dto.SysIsvConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysIsvConfigMapper {
    long countByExample(SysIsvConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysIsvConfig record);

    int insertSelective(SysIsvConfig record);

    List<SysIsvConfig> selectByExample(SysIsvConfigExample example);

    SysIsvConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysIsvConfig record, @Param("example") SysIsvConfigExample example);

    int updateByExample(@Param("record") SysIsvConfig record, @Param("example") SysIsvConfigExample example);

    int updateByPrimaryKeySelective(SysIsvConfig record);

    int updateByPrimaryKey(SysIsvConfig record);
}