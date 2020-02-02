package com.superficial.img.common.config.freemarker;

import com.google.common.base.Joiner;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

@Configuration
public class FreemarkerConfiguration implements ApplicationContextAware {

    private ApplicationContext ctx;

    @SuppressWarnings("NullableProblems")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfig(FreeMarkerProperties properties) {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setPreferFileSystemAccess(false);
        configurer.setTemplateLoaderPath(Joiner.on(",").join(properties.getTemplateLoaderPath()));
        configurer.setResourceLoader(this.ctx);
        Properties settings = new Properties();
        settings.putAll(properties.getSettings());
        configurer.setFreemarkerSettings(settings);
        configurer.setFreemarkerVariables(FTLFunctionBeanPostProcessor.sharedVariables);
        return configurer;
    }
}
