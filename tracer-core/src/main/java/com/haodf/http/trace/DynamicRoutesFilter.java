package com.haodf.http.trace;

import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * ribbon动态路由,
 * https://www.cnblogs.com/zhangjianbin/p/7228317.html
 */
public class DynamicRoutesFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String tag = httpServletRequest.getHeader("HDF-test-version");
        if(tag != null){
            RibbonFilterContextHolder.getCurrentContext().add("HDF-test-version", tag);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}