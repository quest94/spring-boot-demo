package com.quec1994.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <P>ClassName: MybatisPlusConfig</P>
 * <P>Description: Mybatis-Plus配置类</P>
 *
 * @author quec1994
 * @version V1.0, 2019/2/27
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("com.quec1994.mapper.*")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
