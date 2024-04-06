package com.heartbeat.myapp.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@ConditionalOnProperty(name = {"spring.redis.redisson.config"})
public class RedissonLockUtil {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 加锁
     *
     * @param lockKey 锁Key
     * @return RLock
     */
    public RLock lock(String lockKey) {
        RLock rlock = redissonClient.getLock(lockKey);
        rlock.lock();
        return rlock;
    }

    /**
     * 释放锁
     *
     * @param lockKey 锁Key
     */
    public void unlock(String lockKey) {
        RLock rlock = redissonClient.getLock(lockKey);
        rlock.unlock();
    }

    /**
     * 释放锁
     *
     * @param lock 锁对象
     */
    public static void unlock(RLock lock) {
        lock.unlock();
    }

    /**
     * 带超时的锁
     *
     * @param lockKey 锁key
     * @param timeout 超时时间   单位：秒
     * @return RLock
     */
    public RLock lock(String lockKey, int timeout) {
        RLock rLock = redissonClient.getLock(lockKey);
        rLock.lock(timeout, TimeUnit.SECONDS);
        return rLock;
    }

    /**
     * 带超时的锁
     *
     * @param lockKey 锁key
     * @param unit    时间单位
     * @param timeout 超时时间
     * @return RLock
     */
    public RLock lock(String lockKey, int timeout, TimeUnit unit) {
        RLock rLock = redissonClient.getLock(lockKey);
        rLock.lock(timeout, unit);
        return rLock;
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey   锁key
     * @param waitTime  最多等待时间  单位：秒
     * @param leaseTime 上锁后自动释放锁时间  单位：秒
     * @return boolean
     */
    public boolean tryLock(String lockKey, int waitTime, int leaseTime) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey   锁key
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return boolean
     */
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock(waitTime, leaseTime, unit);
    }
}
