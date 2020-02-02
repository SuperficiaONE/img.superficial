package com.superficial.img.common.config.freemarker;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FreemarkerFunction {

    /**
     * freemarker 函数名称
     */
    String value() default "";
}