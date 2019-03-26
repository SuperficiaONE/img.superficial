package com.superficial.img.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.superficial.img.common.tool.CommonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class SpringMvcAutoConfiguration {

  @Bean
  public ObjectMapper objectMapper() {
    return CommonUtil.getObjectMapper();
  }

  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    return new MappingJackson2HttpMessageConverter(objectMapper());
  }
  @Bean
  public EsFactory getEsFactory(){
    return new EsFactory();
  }
  @Bean
  public EsSearch getEsSearch(){
    return new EsSearch();
  }
}
