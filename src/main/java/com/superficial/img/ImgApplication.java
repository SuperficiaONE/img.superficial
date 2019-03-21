package com.superficial.img;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wxc
 *在过滤器做拦截
 * /api/ 开头需要登录
 * /webapi/ 以及其他 开头不需要登录
 * 所有的 接口都支持跨域
 */
@EnableTransactionManagement
@SpringBootApplication
public class ImgApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImgApplication.class, args);
    }

}
