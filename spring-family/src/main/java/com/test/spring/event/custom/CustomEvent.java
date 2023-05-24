package com.test.spring.event.custom;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CustomEvent extends ApplicationEvent {
    private final String name;

    public CustomEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}
