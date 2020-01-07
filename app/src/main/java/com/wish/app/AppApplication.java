package com.wish.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@MapperScan({"com.wish.dao.mapper"})
@EntityScan(basePackages = "com.wish.domain.po")
@ComponentScans({@ComponentScan("com.wish.service.impl"),@ComponentScan("com.wish.web.controller")})
@EnableJpaRepositories(basePackages = {"com.wish.dao"})
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
