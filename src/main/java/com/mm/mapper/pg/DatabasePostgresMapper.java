package com.mm.mapper.pg;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mm.entity.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: DatabasePostgresMapper
 * @author: MKC
 * @date: 2021-11-25 17:44
 */
@Repository
public interface DatabasePostgresMapper {
    /**
     * 分页获取Postgres数据
     * @param page 分页入参
     * @return 分页数据
     */
    IPage<Test> getPageList(Page<Test> page);

    /**
     * 获取Postgres全量数据
     * @return 全量数据
     */
    List<Map<String, Object>> getList();
}
