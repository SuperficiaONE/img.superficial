package com.superficial.img.common.anno;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiVo {
    /**
     * 名称
     */
    String name() default "";
    /**
     *   描述
     */
    String des() default "";
}
