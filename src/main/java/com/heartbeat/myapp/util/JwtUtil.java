package com.heartbeat.myapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    /**
     * 秘钥拼接
     */
    private static final String SECRET_FORMAT = "s:%d|s:%s";

    /**
     * token有效时间：单位(s)
     */
    @Getter
    private static Integer ttl;

    /**
     * token生成盐值
     */
    private static String salt;

    @Value("${jwt.ttl}")
    public void setTtl(Integer ttl) {
        JwtUtil.ttl = ttl;
    }

    @Value("${jwt.salt}")
    public void setSalt(String salt) {
        JwtUtil.salt = salt;
    }

    /**
     *
     * @param token token
     * @return 是否校验成功
     */
    public static Boolean verify(String token) {
        Integer staffId = JwtUtil.getStaffId(token);
        String secret = String.format(SECRET_FORMAT, staffId, salt);
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("staffId", staffId).build();
            //效验TOKEN
            verifier.verify(token);
            log.info("JWT Token校验成功，Params：====token:{}=====staffId:{}=====secret:{}=====", token, staffId, secret);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("JWT Token校验失败，Params：====token:{}=====staffId:{}=====secret:{}=====msg:{}======", token,
                    staffId, secret, e.getMessage());
            return Boolean.FALSE;
        }
    }

    public static Integer getStaffId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim("staffId").asInt();
        } catch (JWTDecodeException e) {
            log.error("JWT Token解析失败，Params：=========token:{}========msg:{}======", token, e.getMessage());
            throw new RuntimeException();
        }
    }

    /**
     * 生成token签名EXPIRE_TIME30分钟后过期
     *
     * @param staffId  职工ID
     * @return 加密的token
     */
    public static String generate(Integer staffId) {
        Date date = new Date(System.currentTimeMillis() + ttl * 1000);
        String secret = String.format(SECRET_FORMAT, staffId, salt);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create().withClaim("staffId", staffId).withExpiresAt(date).sign(algorithm);
        log.info("JWT Token创建成功，Params: ====staffId:{}====secret:{}=====", staffId, secret);

        return token;
    }
}
