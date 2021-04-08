package cn.itcast.service;

import cn.itcast.entity.SysLog;

import java.util.List;

public interface SysLogService {

    List<SysLog> findAll() throws Exception;

    void save(SysLog sysLog) throws Exception;
}
