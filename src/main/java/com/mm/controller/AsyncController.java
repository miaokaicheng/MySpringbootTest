package com.mm.controller;

import com.mm.dto.ResultInfo;
import com.mm.dto.Status;
import com.mm.service.AsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description 同步异步测试
 * @Author MKC
 * @Date 2021/12/30
 */
@Slf4j
@Api(tags = "同步异步测试")
@RestController
public class AsyncController {
    @Autowired
    private AsyncService asyncService;

    @ApiOperation(value = "异步方法测试")
    @GetMapping("async")
    public ResultInfo testAsync() {
        long start = System.currentTimeMillis();
        log.info("异步方法开始");
        Future<String> stringFuture = asyncService.asyncMethod();
        String result = "";
        try {
            //获取返回值，并定义超时时间5s
            result = stringFuture.get(5, TimeUnit.SECONDS);
            log.info("异步方法返回：" + result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("异步返回异常",e);
            return new ResultInfo(Status.SUCCESS.code,"异步方法调用异常",e + ":" + e.getMessage());
        }
        log.info("异步方法结束");
        long end = System.currentTimeMillis();
        log.info("总耗时：{} ms", end - start);
        return new ResultInfo(Status.SUCCESS.code,"异步方法调用成功",result);
    }

    @ApiOperation(value = "同步方法测试")
    @GetMapping("sync")
    public void testSync() {
        long start = System.currentTimeMillis();
        log.info("同步方法开始");
        asyncService.syncMethod();
        log.info("同步方法结束");
        long end = System.currentTimeMillis();
        log.info("总耗时：{} ms", end - start);
    }
}
