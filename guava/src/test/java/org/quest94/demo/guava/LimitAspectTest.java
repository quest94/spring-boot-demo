package org.quest94.demo.guava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

@SpringBootTest(classes = {DemoMavenGuavaApplication.class, LimitAspectTest.ControllerTest.class})
class LimitAspectTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;
    ExecutorService executorService = Executors.newCachedThreadPool();

    @BeforeEach
    void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void around() throws Exception {
        // test warmup
        mockMvc.perform(MockMvcRequestBuilders.post("/limit/warmup"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(RESULT_MESSAGE));
        // test start
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/limit/test")
                .contentType(MediaType.APPLICATION_JSON);
        int sustainedSeconds = 3;
        final long startTimeStamp = System.currentTimeMillis();
        List<CompletableFuture<Boolean>> futureList = new ArrayList<>();
        while (System.currentTimeMillis() - startTimeStamp < sustainedSeconds * 1000) {
            CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
                try {
                    mockMvc.perform(requestBuilder)
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andExpect(MockMvcResultMatchers.content().string(RESULT_MESSAGE));
                    return true;
                } catch (Exception e) {
                    Throwable cause = e.getCause();
                    Assertions.assertInstanceOf(LimitRejuctException.class, cause);
                    Assertions.assertEquals(cause.getMessage(), LIMITED_MESSAGE);
                }
                return false;
            }, executorService);
            futureList.add(future);
            TimeUnit.MILLISECONDS.sleep(Random.from(RandomGenerator.getDefault()).nextInt(50));
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
        long trueCount = futureList.stream()
                .map(CompletableFuture::join)
                .filter(Boolean.TRUE::equals)
                .count();
        Assertions.assertEquals(PERMITS_PER_SECONDS * sustainedSeconds, trueCount);
    }

    public static final String LIMITED_MESSAGE = "触发接口限流，请重试";
    public static final String RESULT_MESSAGE = "请求成功";
    public static final int PERMITS_PER_SECONDS = 4;

    @RestController
    @Slf4j
    public static class ControllerTest {

        @Limit(key = "/limit/warmup", permitsPerSeconds = PERMITS_PER_SECONDS, message = LIMITED_MESSAGE)
        @RequestMapping("/limit/warmup")
        public String before() {
            return RESULT_MESSAGE;
        }

        @Limit(key = "/limit/test", permitsPerSeconds = PERMITS_PER_SECONDS, message = LIMITED_MESSAGE,
                timeout = 0, timeoutTimeUnit = TimeUnit.MICROSECONDS)
        @RequestMapping("/limit/test")
        public String test() {
            return RESULT_MESSAGE;
        }

    }
}