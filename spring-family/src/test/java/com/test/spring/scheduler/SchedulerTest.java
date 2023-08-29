package com.test.spring.scheduler;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerTest {

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        // 按照固定频率执行，每隔3秒跑一次
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long i = new Random().nextLong(1, 5);
                goSleep(i);
                System.out.println(Instant.now() + " :fixedRate "
                    + Thread.currentThread().getName() + "随机数 " + i);
            }
        }, 2, 3, TimeUnit.SECONDS);
        // 按照固定延时执行，上次执行完后隔10秒再跑
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                long i = new Random().nextLong(1, 5);
                goSleep(i);
                System.out.println(Instant.now() + " :fixedDelay "
                    + Thread.currentThread().getName() + "随机数 " + i);
            }
        }, 2, 10, TimeUnit.SECONDS);
    }

    private static void goSleep(long i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //    public static void main(String[] args) {
    //        TimerTask timerTask0 = new TimerTask() {
    //            @Override
    //            public void run() {
    //                long i = new Random().nextLong(5);
    //                goSleep(i);
    //                System.out.println(Instant.now() + " :task0 " + "随机数 " + i);
    //            }
    //            private void goSleep(long i) {
    //                try {
    //                    TimeUnit.SECONDS.sleep(i);
    //                } catch (InterruptedException e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //        };
    //        TimerTask timerTask1 = new TimerTask() {
    //            @Override
    //            public void run() {
    //                long i = new Random().nextLong(10);
    //                goSleep(i);
    //                long j = i / 0;
    //            }
    //            private void goSleep(long i) {
    //                try {
    //                    TimeUnit.SECONDS.sleep(i);
    //                } catch (InterruptedException e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //        };
    //        Timer timer = new Timer();
    //        timer.schedule(timerTask0, 5, 3000);
    //        timer.schedule(timerTask1, 15, 3000);
    //    }

}
