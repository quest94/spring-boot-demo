package org.quest94.demo.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class LimitAspect {

    private static final Cache<String, RateLimiter> RATE_LIMITER_CACHE = CacheBuilder
            .newBuilder()
            // 最大容量，超过容量有对应的淘汰机制
            .maximumSize(1000)
            // 设置元素在最后一次访问多久后过期
            .expireAfterAccess(60 * 5, TimeUnit.SECONDS)
            // 设置并发水平，即允许多少线程无冲突的访问Cache，默认值是4，
            // 该值越大，LocalCache中的segment数组也会越大，访问效率越高，当然空间占用也大一些
            .concurrencyLevel(10)
            .build();

    @Around("@annotation(org.quest94.demo.guava.Limit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 拿limit的注解
        Limit limit = method.getAnnotation(Limit.class);
        if (limit != null) {
            // key作用：不同的接口，不同的流量控制
            String key = limit.key();
            // 取出令牌桶
            RateLimiter rateLimiter = RATE_LIMITER_CACHE.get(key, () -> RateLimiter.create(limit.permitsPerSeconds()));
            // 拿令牌
            boolean acquire = rateLimiter.tryAcquire(limit.timeout(), limit.timeoutTimeUnit());
            // 拿不到令牌，直接返回异常信息
            if (!acquire) {
                log.debug("获取令牌失败，令牌桶：{}", key);
                throw new LimitRejuctException(limit.message());
            }
            log.info("获取令牌成功，令牌桶：{}", key);
        }
        return joinPoint.proceed();
    }

}
