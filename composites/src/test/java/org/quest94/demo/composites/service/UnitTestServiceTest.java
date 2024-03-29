package org.quest94.demo.composites.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
@SpringBootTest
public class UnitTestServiceTest {

    @Autowired
    UnitTestService testService;

    @Test
    public void test() {
        String msg = "this is a test";
        String result = testService.process(msg);
        // 断言 是否和预期一致
        Assertions.assertEquals(msg, result);
    }

//    // 引入 ContiPerf 进行性能测试
//    @Rule
//    public ContiPerfRule contiPerfRule = new ContiPerfRule();
//
//    @Test
//    // 10个线程 执行10次
//    @PerfTest(invocations = 100, threads = 10)
//    public void test2() {
//        String msg = "this is a test";
//        String result = testService.process(msg);
//        // 断言 是否和预期一致
//        Assert.assertEquals(msg, result);
//    }
}