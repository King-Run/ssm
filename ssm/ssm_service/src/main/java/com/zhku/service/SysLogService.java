package com.zhku.service;

import com.zhku.pojo.SysLog;

import java.util.List;

public interface SysLogService {
    public void save(SysLog sysLog);

    public List<SysLog> findAll();
}
