package com.mm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mm.entity.Test;

import java.util.List;
import java.util.Map;

/**
 * @Description: DatabseTestService
 * @author: MKC
 * @date: 2021-12-07 16:14
 */
public interface DatabaseTestService {

    List<Test> getMysqlPageList(Page<Test> page);

    List<Map<String, Object>> getMysql();

    List<Test> getOraclePageList(Page<Test> page);

    List<Map<String, Object>> getOracle();

    List<Test> getPostgresPageList(Page<Test> page);

    List<Map<String, Object>> getPostgres();

    List<Map<String, Object>> getGreenplum();
}
