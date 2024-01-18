package org.quest94.demo.composites.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <P>ClassName: MybatisPlusConfig
 * <P>Description: Mybatis-Plus配置类
 *
 * @author quec1994
 * @version V1.0, 2019/2/27
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("org.quest94.demo.composites.mapper.*")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor();
    }
}
