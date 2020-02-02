package com.superficial.img.common.anno;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ApiAttr {
    /**
     * 默认值
     */
    String value() default "";
    /**
     * 名称
     */
    String name() default "";
    /**
     *   描述
     */
    String des() default "";
}
