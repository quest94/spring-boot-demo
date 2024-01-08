package org.quest94.demo.composites.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Transactional
public class OrderServerImpl {

    @Resource
    private RedissonClient redissonClient;

    @Transactional(rollbackFor = Exception.class)
    public boolean createOrder(String userId, String productId) {

        //  如果不加锁，必然超卖
        RLock lock = redissonClient.getLock("stock:" + productId);

        try {
            lock.lock();
            lock.lock(10, TimeUnit.SECONDS);
            lock.tryLock();
            lock.tryLock(10, TimeUnit.SECONDS);
            lock.tryLock(10, 10, TimeUnit.SECONDS);


        } catch (Exception ex) {
            log.error("下单失败", ex);
        } finally {
            lock.unlock();
        }

        return false;
    }
}
