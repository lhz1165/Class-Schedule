package com.lhz.estest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@MapperScan("com.lhz.estest.mapper")
public class EsTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(EsTestApplication.class, args);
    }

}
