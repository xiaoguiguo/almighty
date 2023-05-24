package com.test.spring.retry.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("retry")
@Tag(name = "重试机制实现")
@Slf4j
public class RetryController {

    /**
     * delay=2000L表示延迟2秒 multiplier=2表示两倍 即第一次重试2秒后,第二次重试4秒后,第三次重试8秒后
     */
    @RequestMapping("test2")
    @Retryable(value = {RemoteAccessException.class}, maxAttempts = 3,
            backoff = @Backoff(delay = 2000L, multiplier = 2))
    public boolean isEnabled() {
        return getTestname(20);
    }

    private boolean getTestname(Integer param) {
        int i = new Random().nextInt(param);
        log.info("随机数：{}", i);
        if (1 == i) {
            return true;
        } else if (i < 1) {
            throw new IllegalArgumentException("参数异常");
        } else if (i < 10) {
            return false;
        } else {
            throw new RemoteAccessException("大于10，抛出异常");
        }
    }

    /**
     * 使用@Recover，在超过重试的最大次数或者抛出了未知异常，会调用这个补偿方法
     */
    @Recover
    public boolean recover2(Exception e, Integer param) {
        log.info("2请求参数为: {}", param);
        log.info("2超过最大重试次数或抛出没有指定重试的异常, e = {} ", e.getMessage());
        return false;
    }

}
