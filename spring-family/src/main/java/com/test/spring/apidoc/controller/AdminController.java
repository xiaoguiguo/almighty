package com.test.spring.apidoc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@Tag(name = "管理", description = "管理详细接口")
public class AdminController {

    @Operation(summary = "管理信息", method = "test")
    @GetMapping("doc")
    public Boolean test() {
        return true;
    }
}
