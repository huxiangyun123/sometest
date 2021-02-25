package com.dj.sometest.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Chris
 * @Date: 2021/1/29 23:08
 */
@Component
@Slf4j
public class MyFiter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("自定义过滤器=============");
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        long startTime = System.currentTimeMillis();
        String token = httpReq.getHeader("token");


        String uri = httpReq.getRequestURI();
        StringBuffer url = httpReq.getRequestURL();
        log.info("request uri:{}", uri);
        log.info("request url:{}", url);
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> headerNames = httpReq.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = httpReq.getHeader(key);
            map.put(key, value);
        }
        log.info("request header:{}", map.toString());


        filterChain.doFilter(servletRequest, servletResponse);

        long endTime = System.currentTimeMillis();
        long executeTime = (endTime - startTime);
        log.info("run time:{} 毫秒", executeTime);
    }


}
