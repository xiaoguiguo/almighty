package com.test.spring.async.controller;

import com.test.spring.async.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("async")
@RequiredArgsConstructor
public class AsyncController {
    private final AsyncService asyncService;

    @RequestMapping("test")
    public void asyncTest() {
        asyncService.service1();
        asyncService.service2();
    }
}
