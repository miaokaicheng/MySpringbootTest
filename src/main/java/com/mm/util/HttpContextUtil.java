package com.mm.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description 获取Http相关的工具类
 * @Author MKC
 * @Date 2021/12/27
 */
public class HttpContextUtil {
    private HttpContextUtil(){

    }

    /**
     * 获取HttpServletRequest请求
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
