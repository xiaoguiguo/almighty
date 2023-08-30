package com.test.spring.scheduler;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "修改定时任务执行时间", description = "修改定时任务执行时间")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("exec-task")
public class ExecTaskController {

    private final ExecTaskScheduler execTaskScheduler;

    @PostMapping("config/update")
    public boolean updateConfig(String cron) {
        log.info("修改 cron = {}", cron);
        execTaskScheduler.setExecTaskCron(cron);
        return true;
    }
}

