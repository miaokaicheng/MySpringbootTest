package com.mm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mm.entity.MyConstants;
import com.mm.entity.Test;
import com.mm.mapper.mysql.DatabaseMysqlMapper;
import com.mm.mapper.oracle.DatabaseOracleMapper;
import com.mm.mapper.pg.DatabasePostgresMapper;
import com.mm.service.DatabaseTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: DatabseTestServiceImpl
 * @author: MKC
 * @date: 2021-12-07 16:14
 */
@SuppressWarnings("unchecked")
@Slf4j
@Service
public class DatabaseTestServiceImpl implements DatabaseTestService {
    @Autowired
    private DatabaseMysqlMapper databaseMysqlMapper;
    @Autowired
    private DatabaseOracleMapper databaseOracleMapper;
    @Autowired
    private DatabasePostgresMapper databasePostgresMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 分页获取Mysql数据
     * @param page 分页入参
     * @return 分页数据
     */
    @Override
    public List<Test> getMysqlPageList(Page<Test> page) {
        IPage<Test> testIPage = databaseMysqlMapper.getPageList(page);
        log.info("当前满足条件总行数：{}", testIPage.getTotal());
        log.info("当前分页总页数：{}", testIPage.getPages());
        log.info("当前页面大小：" + testIPage.getSize());
        log.info("当前页码：" + testIPage.getCurrent());
        List<Test> list = testIPage.getRecords();
        return list;
    }

    /**
     * 获取Mysql全量数据
     * @return 全量数据
     */
    @Override
    public List<Map<String, Object>> getMysql() {
        List<Map<String, Object>> list;
        if(redisTemplate.hasKey(MyConstants.REDIS_MYSQL_LIST)){
            list = redisTemplate.opsForList().range(MyConstants.REDIS_MYSQL_LIST, 0, -1);
        }else{
            list = databaseMysqlMapper.getList();
            list.forEach(m -> redisTemplate.opsForList().rightPush(MyConstants.REDIS_MYSQL_LIST, m));
        }
        list.forEach(System.out::println);
        return list;
    }

    /**
     * 分页获取Oracle数据
     * @param page 分页入参
     * @return 分页数据
     */
    @Override
    public List<Test> getOraclePageList(Page<Test> page) {
        IPage<Test> testIPage = databaseOracleMapper.getPageList(page);
        log.info("当前满足条件总行数：{}", testIPage.getTotal());
        log.info("当前分页总页数：{}", testIPage.getPages());
        log.info("当前页面大小：" + testIPage.getSize());
        log.info("当前页码：" + testIPage.getCurrent());
        List<Test> list = testIPage.getRecords();
        return list;
    }

    /**
     * 获取Oracle全量数据
     * @return 全量数据
     */
    @Override
    public List<Map<String, Object>> getOracle() {
        List<Map<String, Object>> list;
        if(redisTemplate.hasKey(MyConstants.REDIS_ORACLE_LIST)){
            list = redisTemplate.opsForList().range(MyConstants.REDIS_ORACLE_LIST, 0, -1);
        }else{
            list = databaseOracleMapper.getList();
            list.forEach(m -> redisTemplate.opsForList().rightPush(MyConstants.REDIS_ORACLE_LIST, m));
        }
        list.forEach(System.out::println);
        return list;
    }

    /**
     * 分页获取Postgres数据
     * @param page 分页入参
     * @return 分页数据
     */
    @Override
    public List<Test> getPostgresPageList(Page<Test> page) {
        IPage<Test> testIPage = databasePostgresMapper.getPageList(page);
        log.info("当前满足条件总行数：{}", testIPage.getTotal());
        log.info("当前分页总页数：{}", testIPage.getPages());
        log.info("当前页面大小：" + testIPage.getSize());
        log.info("当前页码：" + testIPage.getCurrent());
        List<Test> list = testIPage.getRecords();
        return list;
    }

    /**
     * 获取Postgres全量数据
     * @return 全量数据
     */
    @Override
    public List<Map<String, Object>> getPostgres() {
        List<Map<String, Object>> list;
        if(redisTemplate.hasKey(MyConstants.REDIS_POSTGRES_LIST)){
            list = redisTemplate.opsForList().range(MyConstants.REDIS_POSTGRES_LIST, 0, -1);
        }else{
            list = databasePostgresMapper.getList();
            list.forEach(m -> redisTemplate.opsForList().rightPush(MyConstants.REDIS_POSTGRES_LIST, m));
        }
        list.forEach(System.out::println);
        return list;
    }

    /**
     * 获取Greenplum全量数据
     * Greenplum是基于Postgres的，因为公司有用到所以就集成了一下，本地没装这个数据库，不做实现了，原理都一样
     * @return 全量数据
     */
    @Override
    public List<Map<String, Object>> getGreenplum() {
        return null;
    }
}
