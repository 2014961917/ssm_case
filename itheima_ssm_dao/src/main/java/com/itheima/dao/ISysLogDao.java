package com.itheima.dao;

import com.itheima.domain.SysLog;


import java.util.List;

public interface ISysLogDao {

    public void save(SysLog sysLog) throws Exception;


    List<SysLog> findAll() throws Exception;
}
