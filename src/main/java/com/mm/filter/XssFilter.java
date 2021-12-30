package com.mm.filter;

import com.mm.util.XssHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description Xss攻击拦截器
 * @Author MKC
 * @Date 2021/12/30
 */
@Slf4j
public class XssFilter implements Filter {
    /**
     * 是否过滤富文本内容
     */
    private static boolean IS_INCLUDE_RICH_TEXT = false;

    public List<String> excludes = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("------------ xss filter init ------------");
        String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
        if (StringUtils.isNotBlank(isIncludeRichText)) {
            IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
        }
        String temp = filterConfig.getInitParameter("excludes");
        if (temp != null) {
            String[] url = temp.split(",");
            excludes.addAll(Arrays.asList(url));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (handleExcludeUrl(req, resp)) {
            chain.doFilter(request, response);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request,
                IS_INCLUDE_RICH_TEXT);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {

    }

    private boolean handleExcludeUrl(HttpServletRequest request, HttpServletResponse response) {
        if (excludes == null || excludes.isEmpty()) {
            return false;
        }
        String url = request.getServletPath();
        for (String pattern : excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }
}
