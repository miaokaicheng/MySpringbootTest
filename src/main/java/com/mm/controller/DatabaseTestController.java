package com.mm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mm.annotation.Log;
import com.mm.dto.ResultInfo;
import com.mm.dto.Status;
import com.mm.entity.Test;
import com.mm.service.DatabaseTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @Description: 多数据源测试
 * @author: MKC
 * @date: 2021-12-03 17:31
 */
@Api(tags = "多数据库")
@RestController
@RequestMapping("/database")
public class DatabaseTestController {
    @Autowired
    private DatabaseTestService databaseTestService;

    /**
     * 分页获取Mysql数据
     *
     * paramType：表示参数放在哪个地方
     *     header-->请求参数的获取：@RequestHeader(代码中接收注解)
     *     query-->请求参数的获取：@RequestParam(代码中接收注解)
     *     path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
     *     body-->请求参数的获取：@RequestBody(代码中接收注解)
     *     form（不常用）
     * PathVariable就是和 paramType = "path" 联合使用的
     * @return 分页数据
     */
    @ApiOperation(value = "分页获取Mysql数据", notes = "分页获取Mysql数据")
    //@ApiImplicitParam(name = "current", value = "当前页数", required = true, dataType = "Long", paramType = "path")
    @Log("执行方法：getMysqlPageList")
    @GetMapping(value = "/mysqlPageList")
    //public ResultInfo getMysqlPageList(@PathVariable(value = "current") Long current, @PathVariable(value = "size")  Long size) {
    public ResultInfo getMysqlPageList( Long current, Long size) {
        List<Test> list = databaseTestService.getMysqlPageList(new Page<>(current, size));
        if (list != null && list.size() > 0) {
            return new ResultInfo(Status.SUCCESS.code, "查询成功", list);
        } else {
            return new ResultInfo(Status.UNKNOWN_ERROR.code, "查询失败");
        }
    }

    /**
     * 获取Mysql全量数据
     * @return 全量数据
     */
    @ApiOperation(value = "获取Mysql全量数据", notes = "获取Mysql全量数据")
    @GetMapping(value = "/mysql")
    public List<Map<String,Object>> getMysql() {
        return databaseTestService.getMysql();
    }

    /**
     * 分页获取Oracle数据
     * @return 分页数据
     */
    @ApiOperation(value = "分页获取Oracle数据", notes = "分页获取Oracle数据")
    @GetMapping(value = "/oraclePageList")
    public ResultInfo getOraclePageList() {
        List<Test> list = databaseTestService.getOraclePageList(new Page<>(1, 3));
        if (list != null && list.size() > 0) {
            return new ResultInfo(Status.SUCCESS.code, "查询成功", list);
        } else {
            return new ResultInfo(Status.UNKNOWN_ERROR.code, "查询失败");
        }
    }

    /**
     * 获取Oracle全量数据
     * @return 全量数据
     */
    @ApiOperation(value = "获取Oracle全量数据", notes = "获取Oracle全量数据")
    @GetMapping(value = "/oracle")
    public List<Map<String,Object>> getOracle() {
        return databaseTestService.getOracle();
    }

    /**
     * 分页获取Postgres数据
     * @return 分页数据
     */
    @ApiOperation(value = "分页获取Postgres数据", notes = "分页获取Postgres数据")
    @GetMapping(value = "/postgresPageList")
    public ResultInfo getPostgresPageList() {
        List<Test> list = databaseTestService.getPostgresPageList(new Page<>(1, 4));
        if (list != null && list.size() > 0) {
            return new ResultInfo(Status.SUCCESS.code, "查询成功", list);
        } else {
            return new ResultInfo(Status.UNKNOWN_ERROR.code, "查询失败");
        }
    }

    /**
     * 获取Postgres全量数据
     * @return 全量数据
     */
    @ApiOperation(value = "取Postgres全量数据", notes = "取Postgres全量数据")
    @GetMapping(value = "/postgres")
    public List<Map<String,Object>> getPostgres() {
        return databaseTestService.getPostgres();
    }

    /**
     * 获取Greenplum全量数据
     * @return 全量数据
     */
    @ApiIgnore
    @GetMapping(value = "/greenplum")
    public List<Map<String,Object>> getGreenplum() {
        return databaseTestService.getGreenplum();
    }
}
