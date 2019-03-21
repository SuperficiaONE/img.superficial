package com.superficial.img.common.config;

import com.superficial.img.common.interceptor.ErrorPageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private ErrorPageInterceptor errorPageInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(errorPageInterceptor);
        //.addPathPatterns("/action/**", "/mine/**");默认所有
        super.addInterceptors(registry);
    }
}
