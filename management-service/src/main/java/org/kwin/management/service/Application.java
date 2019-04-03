package org.kwin.management.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Kwin
 * @Description:
 * @Date: Create in 11:19 2019-03-31
 */
@SpringBootApplication
@MapperScan("org.kwin.management.dao.mapper.**")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
