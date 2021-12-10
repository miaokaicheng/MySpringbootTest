package com.mm.mapper.mysql;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description: TestMapper
 * @author: MKC
 * @date: 2021-11-25 17:44
 */
@Repository
public interface TestMapper {
    List<Map<String, Object>> getList();

    void update(Map<String, Object> map);

    List<Map<String, Object>> getList2();

    List<BigDecimal> getList3(Map<String, Object> map);

    void update2(Map<String, Object> map);
}
