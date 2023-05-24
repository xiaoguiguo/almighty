package com.test.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 通过@EventListener注解
 */

@Component
@Slf4j
public class MyEventListener {

    @EventListener
    public void doSomethingWhenApplicationStart(ApplicationReadyEvent event) {
        log.info("[event listener] listen by annotation: @EventListener");
    }
}
