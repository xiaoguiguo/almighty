package com.test.spring.apidoc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "测试文档")
@RequestMapping("test")
public class DocController {

    @RequestMapping("doc")
    @Operation(method = "test", summary = "获取doc", description = "doc详细信息")
    public String test() {
        return "test doc";
    }
}
