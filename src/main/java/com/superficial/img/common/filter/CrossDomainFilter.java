package com.superficial.img.common.filter;

import cn.hutool.http.HttpUtil;
import com.superficial.img.common.Cons;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@WebFilter(urlPatterns = {"/api/*","/webapi/*"}, filterName = "CrossDomainFilter")
public class CrossDomainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse  httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "accept,content-type");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
        StringBuilder sb = new StringBuilder();
        if( !checkUrlStatic(HttpUtils.currentUrl())){
            sb.append("当前的url: 【");
            sb.append(HttpUtils.currentUrl());
            sb.append("】");
            log.info(sb.toString());
        }
        chain.doFilter(request, httpServletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     *   校验url 是否是静态文件路径
     *   true : 是
     *   false: 否
     */
    public  boolean checkUrlStatic(String url){
        if(CommonUtil.isEmpty(url)){
            return true;
        }
        return url.endsWith(Cons.STATIC_JS) || url.endsWith(Cons.STATIC_CSS);
    }
}
