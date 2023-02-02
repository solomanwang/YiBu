package com.yibu.getaway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName : InitResourceCacheRunner
 * @Description : 初始化资源数据
 * @Author : wzq
 * @Date: 2023-02-02 09:26
 */
@Component
@Slf4j
public class InitResourceCacheRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        //TODO 记载资源数据
        log.info("开始加载资源数据....");
    }
}
