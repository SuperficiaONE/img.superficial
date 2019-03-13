package com.superficial.img;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wxc
 */
@EnableTransactionManagement
@SpringBootApplication
public class ImgApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImgApplication.class, args);
    }

}
