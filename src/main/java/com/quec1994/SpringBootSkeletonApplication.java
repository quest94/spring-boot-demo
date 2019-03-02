package com.quec1994;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <P>ClassName: SpringBootSkeletonApplication
 * <P>Description: spring boot 启动类
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@SpringBootApplication
@ServletComponentScan
@Slf4j
public class SpringBootSkeletonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSkeletonApplication.class, args);
        log.info("spring-boot-skeleton 服务启动完成");
    }
}
