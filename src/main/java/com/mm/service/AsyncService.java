package com.mm.service;

import java.util.concurrent.Future;

/**
 * @Description 同步异步测试
 * @Author MKC
 * @Date 2021/12/30
 */
public interface AsyncService {
    /**
     * 异步方法
     * @return 返回
     */
    Future<String> asyncMethod();

    /**
     * 同步方法
     */
    void syncMethod();
}
