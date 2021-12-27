package com.mm.mapper.mysql;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mm.entity.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 可以继承或者不继承BaseMapper
 * @author: MKC
 * @date: 2021-11-25 17:44
 */
@Repository
public interface DatabaseMysqlMapper {
    /**
     * 分页获取Mysql数据
     * @param page 分页入参
     * @return 分页数据
     */
    IPage<Test> getPageList(Page page);

    List<Map<String, Object>> getList();
}
