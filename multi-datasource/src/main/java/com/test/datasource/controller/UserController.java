package com.test.datasource.controller;

import com.test.datasource.entity.User;
import com.test.datasource.service.source.SourceUserService;
import com.test.datasource.service.goal.GoalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final SourceUserService sourceUserService;
    private final GoalUserService goalUserService;

    @GetMapping("test")
    public String testUser() {
        // 查询source数据库中的数据
        User sourceUser = sourceUserService.findById(1L);
        // 查询target数据库中的数据
        User targetUser = goalUserService.findById(1L);
        return sourceUser.getName() + ":" + targetUser.getName();
    }
}
