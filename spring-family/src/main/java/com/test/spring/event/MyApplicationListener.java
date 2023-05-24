package com.test.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 实现ApplicationEvent接口
 */

@Component
@Slf4j
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("[application ready event] listen by ApplicationListener interface");
    }
}
