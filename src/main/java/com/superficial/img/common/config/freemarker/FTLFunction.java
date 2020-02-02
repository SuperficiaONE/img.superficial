package com.superficial.img.common.config.freemarker;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FTLFunction {

     @Resource
     private FTLFunctionProperties ftlFunctionProperties;

    /**
     * 这是一个很好的案例
     * 自定义 freemarker 函数
     */
    @FreemarkerFunction("s0")
    public String getSource(String value) {
        return value + "?version=" + ftlFunctionProperties.getVersion();
    }

}
