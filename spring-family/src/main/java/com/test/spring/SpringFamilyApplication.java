package com.test.spring;

/*
 * @className: SpringFamilyApplication
 * @author: doudou
 * @datetime: 2023/5/24
 * @description:
 *  spring框架 相关功能 实现
 *  @EnableRetry 重试、轮询实现 {@link com.test.spring.retry}
 *  @EnableAsync 异步执行 实现 {@link com.test.spring.async}
 *  @EnableScheduling 定时任务相关 实现 {@link com.test.spring.scheduler}
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRetry
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringFamilyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringFamilyApplication.class, args);
    }
}
