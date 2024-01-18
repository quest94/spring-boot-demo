package org.quest94.demo.composites;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <P>ClassName: SpringBootDemoApplication
 * <P>Description: spring boot 启动类
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@SpringBootApplication
@Slf4j
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
        log.info("spring-boot-skeleton 服务启动完成");
    }
}
