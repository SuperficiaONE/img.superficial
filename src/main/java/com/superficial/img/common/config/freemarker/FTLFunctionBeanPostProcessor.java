package com.superficial.img.common.config.freemarker;

import com.google.common.base.Strings;
import com.superficial.img.common.tool.CommonUtil;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@EnableConfigurationProperties(FTLFunctionProperties.class)
@Configuration
public class  FTLFunctionBeanPostProcessor implements BeanPostProcessor {


     private  FTLFunctionProperties ftlFunctionProperties;

    public FTLFunctionBeanPostProcessor(FTLFunctionProperties ftlFunctionProperties) {
        this.ftlFunctionProperties = ftlFunctionProperties;
    }

    public static final Map<String, Object> sharedVariables = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        Class<?> objClz =bean.getClass();
         /*
        log.info(CommonUtil.sb()
                .append(bean.getClass().toString())
                .append(":")
                .append("beanName[")
                .append(beanName)
                .append("]").toString());*/
        if(!beanName.equals(ftlFunctionProperties.getFunctionClassName())){
            //加快运行
            return bean;
        }
        for (Method method : objClz.getDeclaredMethods()) {
            FreemarkerFunction ff = method.getAnnotation(FreemarkerFunction.class);
            if (ff != null && !Strings.isNullOrEmpty(ff.value())) {
                sharedVariables.put(ff.value(), new FunctionInvoker(bean, method));
            }
        }
        return bean;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


    public static class FunctionInvoker implements TemplateMethodModelEx {

        private final Object bean;
        private final Method method;

        public FunctionInvoker(Object bean, Method method) {
            this.bean = bean;
            this.method = method;
        }

        @SuppressWarnings("RedundantThrows")
        @Override
        public Object exec(List arguments) throws TemplateModelException {
            try {
                Object[] array = new Object[arguments.size()];
                arguments.toArray(array);
                for (int i = 0; i < array.length; i++) {
                    if (array[i] instanceof SimpleScalar) {
                        array[i] = ((SimpleScalar) array[i]).getAsString();
                    }
                }
                if (array.length == 1) {
                    return method.invoke(bean, array[0]);
                } else {
                    return method.invoke(bean, array);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
