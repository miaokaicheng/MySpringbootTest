package com.mm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public List<Map<String, Object>> getMysql() {
        List<Map<String, Object>> list;
        if(redisTemplate.hasKey("mysql:list")){
            list = redisTemplate.opsForList().range("mysql:list", 0, -1);
        }else{
            list = databaseMysqlMapper.getList();
            list.forEach(m -> redisTemplate.opsForList().rightPush("mysql:list", m));
        }
        list.forEach(System.out::println);
        return list;
    }

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

    @Override
    public List<Map<String, Object>> getOracle() {
        List<Map<String, Object>> list;
        if(redisTemplate.hasKey("oracle:list")){
            list = redisTemplate.opsForList().range("oracle:list", 0, -1);
        }else{
            list = databaseOracleMapper.getList();
            list.forEach(m -> redisTemplate.opsForList().rightPush("oracle:list", m));
        }
        list.forEach(System.out::println);
        return list;
    }

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

    @Override
    public List<Map<String, Object>> getPostgres() {
        List<Map<String, Object>> list;
        if(redisTemplate.hasKey("postgres:list")){
            list = redisTemplate.opsForList().range("postgres:list", 0, -1);
        }else{
            list = databasePostgresMapper.getList();
            list.forEach(m -> redisTemplate.opsForList().rightPush("postgres:list", m));
        }
        list.forEach(System.out::println);
        return list;
    }

    @Override
    public List<Map<String, Object>> getGreenplum() {
        return null;
    }
}
