package com.test.spring.event.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomEventListener {

    @EventListener
    public void myEventProcessor(CustomEvent event) {
        String name = event.getName();
        log.info("[my event listener] listen, name: {}", name);
    }
}
