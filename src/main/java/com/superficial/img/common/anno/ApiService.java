package com.superficial.img.common.anno;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiService {
    /**
     *  参数的含义
     */
     String params() default "";

    /**
     * 逻辑
     */
    String rule() default "";

    /**
     *  返回值 说明
     */
     String getDes() default "";
}
