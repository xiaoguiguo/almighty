package com.test.spring.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SchedulerService {


    /**     * 每分钟的第30秒跑一次     */
    @Scheduled(cron = "30 * * * * ?")
    public void task1() throws InterruptedException {
        String lockName = "task1";
        if (tryLock(lockName, 30)) {
            System.out.println("hello cron");
            releaseLock(lockName);
        } else {
            return;
        }
    }

    private boolean tryLock(String lockName, long expiredTime) {
        //TODO
        return true;
    }

    private void releaseLock(String lockName) {
        //TODO
    }

//    @Scheduled(cron = "*/5 * * * *  ?")
//    public void testSchedule() {
//        long i = new Random().nextLong(1, 7);
//        sleepSecond(i);
//        log.info(Instant.now() + " :task0 " + "耗时 " + i + " " + Thread.currentThread().getName());
//    }
//
//    @Scheduled(fixedDelay = 10 * 1000)
//    public void testSchedule2() {
//        log.info(Instant.now() + " : task2 " + "耗时 " + 15 + " " + Thread.currentThread().getName());
//        sleepSecond(15);
//        int j = 1 / 0;
//    }

    //    @Scheduled(fixedDelay = 3 * 1000)
    //    @Scheduled(fixedRate = 5 * 1000)
    //    @Scheduled(cron = "0/5 * * * *  ?")

    private void sleepSecond(long i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//    /**
//     * 方法执行完成后，每隔5s执行一次
//     */
//    @Scheduled(fixedDelay = 5000)
//    public void test1() {
//        log.info("test1: 隔五秒, date:{}", LocalDateTime.now());
//    }
//
//    /**
//     * 每隔3秒执行一次方法
//     */
//    @Scheduled(fixedRate = 3000)
//    public void test2() {
//        log.info("隔三秒, date:{}", LocalDateTime.now());
//    }
//
//    /**
//     * 表示每天8时30分0秒执行
//     */
//    @Scheduled(cron = "0 30 8 * * ?")
//    public void test3() {
//        log.info(LocalDateTime.now() + " ...>>cron....");
//    }

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
