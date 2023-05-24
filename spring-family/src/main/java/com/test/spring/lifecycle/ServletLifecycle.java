package com.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class ServletLifecycle {

    /**
     * 启动服务会执行
     */
    @PostConstruct
    public void start() {
        log.info("[service start] hello");
    }

    /**
     * 停止服务会执行
     */
    @PreDestroy
    public void end() {
        log.info("[service end] bye");
    }
}
