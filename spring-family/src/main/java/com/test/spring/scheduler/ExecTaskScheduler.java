package com.test.spring.scheduler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@Getter
@Setter
public class ExecTaskScheduler implements SchedulingConfigurer {

    @Value("${scheduler.execTaskCron}")
    private String execTaskCron;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 使用cron表达式可以动态地设置循环间隔
        taskRegistrar.addTriggerTask(() -> {
            log.info("开始时间： {}", Instant.now());
            // 定时任务具体业务逻辑，模拟业务逻辑处理
            sleepSecond(3);
            log.info("结束时间： {}", Instant.now());
        }, triggerContext -> {
            // 使用CronTrigger触发器，可动态修改cron表达式来操作循环规则
            CronTrigger cronTrigger = new CronTrigger(execTaskCron);
            return cronTrigger.nextExecutionTime(triggerContext);
        });

        taskRegistrar.setScheduler(getExecutor());
    }

    @Bean
    public Executor getExecutor(){
        return new ScheduledThreadPoolExecutor(5);
    }

    private void sleepSecond(long i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
