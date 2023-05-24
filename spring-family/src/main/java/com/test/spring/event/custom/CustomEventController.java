package com.test.spring.event.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
public class CustomEventController {

    final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public CustomEventController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping("test")
    public void eventTest() {
        applicationEventPublisher.publishEvent(new CustomEvent(this, "testName"));
    }
}
