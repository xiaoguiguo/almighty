package com.test.spring.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class SchedulerService {

    /**
     * 方法执行完成后，每隔5s执行一次
     */
//    @Scheduled(fixedDelay = 5000)
    public void test1() {
        log.info("test1: 隔五秒, date:{}", LocalDateTime.now());
    }

    /**
     * 每隔3秒执行一次方法
     */
//    @Scheduled(fixedRate = 3000)
    public void test2() {
        log.info("隔三秒, date:{}", LocalDateTime.now());
    }

    /**
     * 表示每天8时30分0秒执行
     */
//    @Scheduled(cron = "0 30 8 * * ?")
    public void test3() {
        log.info(LocalDateTime.now() + " ...>>cron....");
    }

    /**
     * 每五分钟执行一次
     */
//    @Scheduled(cron = "0 */5 * * * ?")
    /**
     * 每天0点，执行一次
     */
//    @Scheduled(cron = "0 0 0 * * ?")
    // 0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时
}
