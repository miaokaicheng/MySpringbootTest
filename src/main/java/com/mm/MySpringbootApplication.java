package com.mm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Springboot启动类
 *
 * @author MKC
 */
@MapperScan("com.mm.**.mapper")
@EnableAsync
@SpringBootApplication
public class MySpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringbootApplication.class, args);
    }

}



