package org.quest94.demo.guava;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Limit {

    /**
     * 资源主键
     */
    String key() default "";

    /**
     * 最多访问次数,代表请求总数量,即每秒只允许有 permitsPerSeconds 个请求总数量访问，超过的将被限制不能访问
     */
    double permitsPerSeconds();

    /**
     * 等待获取令牌的超时时间
     */
    long timeout() default 0;

    /**
     * 等待获取令牌的超时时间单位
     */
    TimeUnit timeoutTimeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 提示信息
     */
    String message() default "系统繁忙,请稍后重试";

}