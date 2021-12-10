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
    IPage<Test> getPageList(Page<Test> page);

    List<Map<String, Object>> getList();
}
