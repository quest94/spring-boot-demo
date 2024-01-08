package org.quest94.demo.composites.config.profile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * <P>ClassName: ProfileBean
 * <P>Description: 多环境下加载不同的bean测试
 *
 * @author quec1994
 * @version V1.0, 2019/1/25
 **/
@Profile("prod")//支持数组:@Profile({"dev","test"})
@Configuration
@Slf4j
public class ProfileBean {

    @PostConstruct
    public void init() {
        log.info("dev环境下激活");
    }
}