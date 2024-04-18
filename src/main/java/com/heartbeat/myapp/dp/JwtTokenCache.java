package com.heartbeat.myapp.dp;

import com.heartbeat.myapp.constant.AuthConstant;
import com.heartbeat.myapp.util.JwtUtil;
import com.heartbeat.myapp.util.RedissonCacheUtil;
import com.heartbeat.myapp.util.SpringContextUtil;

public class JwtTokenCache {

    private final String jwtToken;

    private final RedissonCacheUtil redissonCacheUtil;

    private final String cacheKey;

    private static final Long KEY_NOT_EXISTS_TTL = -2L;

    private static final Long REFRESH_KEY_TTL = 1800L;

    public JwtTokenCache(String jwtToken) {
        this.jwtToken = jwtToken;
        this.cacheKey = String.format(AuthConstant.STAFF_TOKEN_FORMAT, jwtToken);
        this.redissonCacheUtil = SpringContextUtil.getBean(RedissonCacheUtil.class);
    }

    /**
     * token是否有效
     * @return Boolean
     */
    public Boolean isTokenValid() {
        Integer staffId = redissonCacheUtil.get(this.cacheKey, Integer.class);
        return staffId != null;
    }

    /**
     * 获取缓冲中的StaffId
     * @return Integer
     */
    public Integer getStaffId() {
        return redissonCacheUtil.get(this.cacheKey, Integer.class);
    }

    /**
     * 缓存Key并设置过期时间
     */
    public void save() {
        Integer staffId = JwtUtil.getStaffId(this.jwtToken);
        this.redissonCacheUtil.set(this.cacheKey, staffId, JwtUtil.getTtl());
    }

    /**
     * 刷新Redis相应key的缓存，如果key的有效期低于30分钟
     * 则增加30分钟过期时间
     */
    public void refreshIfNeed() {
        Long ttl = this.redissonCacheUtil.getTTL(this.cacheKey);
        // ttl等于-2说明key不存在或者当前key不满足刷新条件
        if (ttl.equals(KEY_NOT_EXISTS_TTL) || ttl >= REFRESH_KEY_TTL) {
            return;
        }

        this.redissonCacheUtil.set(this.cacheKey, getStaffId(), REFRESH_KEY_TTL);
    }
}
