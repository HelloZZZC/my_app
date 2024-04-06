package com.heartbeat.myapp.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConditionalOnProperty(name = {"spring.redis.redisson.config"})
@Slf4j
public class RedissonCacheUtil {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * @param key    缓存key
     * @param tClass 返回类型
     * @param <T>
     * @return 缓存数据
     */
    public <T> T get(String key, Class<T> tClass) {
        try {
            RBucket<T> bucket = this.redissonClient.getBucket(key);
            return bucket.get();
        } catch (Exception e) {
            log.error("查询发生异常:{}", e.getMessage());
            return null;
        }
    }

    /**
     * @param key   缓存key
     * @param value 设置的值
     */
    public void set(String key, Object value) {
        try {
            this.redissonClient.getBucket(key).set(value);
        } catch (Exception e) {
            log.error("保存普通缓存发生异常:{}", e.getMessage());
        }

    }

    /**
     * @param key   缓存key
     * @param value 设置的值
     * @param time  过期时间：单位(s)
     */
    public void set(String key, Object value, long time) {
        try {
            long expireTime = Math.max(time, 0L);
            this.redissonClient.getBucket(key).set(value, Duration.ofSeconds(expireTime));
        } catch (Exception e) {
            log.error("保存普通缓存发生异常:{}", e.getMessage());
        }
    }
}
