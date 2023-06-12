package com.test.spring.framework.log.controller;

import com.test.spring.framework.exception.ResultResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping("success")
    public String testSuccessLog() {
        return "test success log";
    }

    @PutMapping("error")
    public String testFailLog() throws Exception {
        throw new Exception("error operation");
    }

    /**
     * 返回值格式 统一处理
     * @return
     */
    @GetMapping("log")
    public ResultResponse<String> log() {
        return ResultResponse.success("log");
    }
}
