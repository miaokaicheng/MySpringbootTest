package com.mm.service.impl;

import com.mm.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Description 同步异步测试
 * @Author MKC
 * @Date 2021/12/30
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {
    /**
     * 异步方法,使用了线程池，加上指定线程Bean即可（也可以不加，默认会取线程池中的配置）
     * 返回值需要使用Future接收
     */
    @Async("asyncThreadPoolTaskExecutor")
    @Override
    public Future<String> asyncMethod() {
        sleep();
        log.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<>("1");
    }

    /**
     * 同步方法
     */
    @Override
    public void syncMethod() {
        sleep();
    }
    
    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
