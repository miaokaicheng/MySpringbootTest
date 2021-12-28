package com.mm.mapper.mysql;

import com.mm.dto.SysLog;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author MKC
 * @Date 2021/12/27
 */
@Repository
public interface SysLogMapper {
    void insertSysLog(SysLog syslog);
}
