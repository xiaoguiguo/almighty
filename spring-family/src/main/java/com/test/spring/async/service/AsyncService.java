package com.test.spring.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AsyncService {
    /**
     * 使用的是自定义的线程池 {@link com.test.spring.async.ThreadPoolTaskConfig}
     */
    @Async
    public void service1() {
        log.info(Thread.currentThread().getName() + "service 1 start");
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "service 1 end");
    }
    @Async
    public void service2() {
        log.info(Thread.currentThread().getName() + "service 2 start");
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "service 2 end");
    }
}
