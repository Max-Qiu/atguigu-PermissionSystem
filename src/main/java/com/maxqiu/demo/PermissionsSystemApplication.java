package com.maxqiu.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 尚硅谷 - 权限系统
 *
 * @author Max_Qiu
 */
@MapperScan("com.maxqiu.demo.mapper")
@SpringBootApplication
public class PermissionsSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionsSystemApplication.class, args);
    }
}
