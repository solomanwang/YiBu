package com.yibu.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wzq
 * Date      2020/5/11 23:36
 * Describe:
 */
@SpringBootApplication(scanBasePackages = "com.yibu")
@EnableDiscoveryClient
@MapperScan("com.yibu.goods.mapper")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }
}
