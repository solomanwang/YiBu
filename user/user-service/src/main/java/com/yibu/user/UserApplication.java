package com.yibu.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wzq
 * Date      2020/5/11 14:45
 * Describe:
 */
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.yibu")
@EnableDiscoveryClient
@MapperScan("com.yibu")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
