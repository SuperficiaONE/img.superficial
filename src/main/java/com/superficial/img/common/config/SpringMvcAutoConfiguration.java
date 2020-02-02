package com.superficial.img.common.config;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.superficial.img.common.tool.CommonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringMvcAutoConfiguration {


    @Bean
    public ObjectMapper objectMapper() {
        return CommonUtil.getObjectMapper();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        final MappingJackson2HttpMessageConverter a = new MappingJackson2HttpMessageConverter(objectMapper());
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        a.setSupportedMediaTypes(supportedMediaTypes);
        return a;
    }

    @Bean
    public EsFactory getEsFactory() {
        return new EsFactory();
    }

    @Bean
    public EsSearch getEsSearch() {
        return new EsSearch();
    }



}
