package com.superficial.img.common.anno;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Api {
    /**
     *  接口描述
     */
    String value() default "";
    /**
     * 接口参数
     */
    String url() default "";
}
