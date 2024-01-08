package org.quest94.demo.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RateLimiterTest {

    @Test
    public void acquireTest() {
        // 每秒固定生成5个令牌
        RateLimiter rateLimiter = RateLimiter.create(5);
        final long startTimeStamp = System.currentTimeMillis();
        long preStartTimeStamp = startTimeStamp;
        for (int i = 0; i < 10; i++) {
            // 取出1个令牌
            double acquireWaitTime = rateLimiter.acquire();
            long currStartTimeStamp = System.currentTimeMillis();
            double intervalTime = currStartTimeStamp - preStartTimeStamp;
            String message = doSomeThing(currStartTimeStamp, startTimeStamp, i, intervalTime, acquireWaitTime);
            if (i == 0) {
                Assertions.assertTrue(intervalTime >= 0 && intervalTime < 10, message);
            } else {
                Assertions.assertTrue(intervalTime > 180 && intervalTime < 220, message);
            }
            preStartTimeStamp = currStartTimeStamp;
        }
    }

    @Test
    public void warmupTest() {
        // 每秒固定生成5个令牌，预热时间3秒
        RateLimiter rateLimiter = RateLimiter.create(5, 3, TimeUnit.SECONDS);
        final long startTimeStamp = System.currentTimeMillis();
        long preStartTimeStamp = startTimeStamp;
        for (int i = 0; i < 15; i++) {
            // 取出1个令牌
            double acquireWaitTime = rateLimiter.acquire();
            long currStartTimeStamp = System.currentTimeMillis();
            double intervalTime = currStartTimeStamp - preStartTimeStamp;
            String message = doSomeThing(currStartTimeStamp, startTimeStamp, i, intervalTime, acquireWaitTime);
            if (i == 0) {
                Assertions.assertTrue(intervalTime >= 0 && intervalTime < 10f, message);
            } else if (i <= 2) {
                Assertions.assertTrue(intervalTime > 500 && intervalTime < 600, message);
            } else if (i <= 4) {
                Assertions.assertTrue(intervalTime > 400 && intervalTime < 500, message);
            } else if (i <= 7) {
                Assertions.assertTrue(intervalTime > 250 && intervalTime < 400, message);
            } else {
                Assertions.assertTrue(intervalTime > 180 && intervalTime < 220, message);
            }
            preStartTimeStamp = currStartTimeStamp;
        }
    }

    @Test
    public void preConsumptionTest() {
        // 每秒固定生成5个令牌
        RateLimiter rateLimiter = RateLimiter.create(5);
        final long startTimeStamp = System.currentTimeMillis();
        long preStartTimeStamp = startTimeStamp;
        for (int i = 0; i < 5; i++) {
            // 一次性取出10个令牌
            double acquireWaitTime = rateLimiter.acquire(10);
            long currStartTimeStamp = System.currentTimeMillis();
            double intervalTime = currStartTimeStamp - preStartTimeStamp;
            String message = doSomeThing(currStartTimeStamp, startTimeStamp, i, intervalTime, acquireWaitTime);
            if (i == 0) {
                Assertions.assertTrue(intervalTime >= 0 && intervalTime < 10, message);
            } else {
                Assertions.assertTrue(intervalTime > 1980 && intervalTime < 2020, message);
            }
            preStartTimeStamp = currStartTimeStamp;
        }
    }

    private String doSomeThing(long currStartTimeStamp, long startTimeStamp, int i, double intervalTime, double acquireWaitTime) {
        long totalTime = currStartTimeStamp - startTimeStamp;
        String message = String.format("开始执行第%s轮，间隔时间%sms，等待时间:%ss，总时间:%sms", i, intervalTime, acquireWaitTime, totalTime);
        log.info(message);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

}