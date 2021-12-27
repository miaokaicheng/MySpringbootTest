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

    /**
     * 分页获取Mysql数据
     * @param page 分页入参
     * @return 分页数据
     */
    List<Test> getMysqlPageList(Page<Test> page);

    /**
     * 获取Mysql全量数据
     * @return 全量数据
     */
    List<Map<String, Object>> getMysql();

    /**
     * 分页获取Oracle数据
     * @param page 分页入参
     * @return 分页数据
     */
    List<Test> getOraclePageList(Page<Test> page);

    /**
     * 获取Oracle全量数据
     * @return 全量数据
     */
    List<Map<String, Object>> getOracle();

    /**
     * 分页获取Postgres数据
     * @param page 分页入参
     * @return 分页数据
     */
    List<Test> getPostgresPageList(Page<Test> page);

    /**
     * 获取Postgres全量数据
     * @return 全量数据
     */
    List<Map<String, Object>> getPostgres();

    /**
     * 获取Greenplum全量数据
     * @return 全量数据
     */
    List<Map<String, Object>> getGreenplum();
}
