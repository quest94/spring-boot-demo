package com.quec1994;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <P>ClassName: DemoApplication</P>
 * <P>Description: spring boot 启动类</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@SpringBootApplication
@ServletComponentScan
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        log.info("demo 服务启动完成");
    }
}
