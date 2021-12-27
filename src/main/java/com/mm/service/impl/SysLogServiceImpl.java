package com.mm.service.impl;

import com.mm.dto.SysLog;
import com.mm.mapper.mysql.SysLogMapper;
import com.mm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author MKC
 * @Date 2021/12/27
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * 日志测试
     * @param syslog
     */
    @Override
    public void saveSysLog(SysLog syslog) {
        sysLogMapper.insertSysLog(syslog);
    }
}
