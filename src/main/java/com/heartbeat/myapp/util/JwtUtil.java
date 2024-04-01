package com.heartbeat.myapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {

    /**
     * 过期时间：30分钟
     */
    private static final Integer EXPIRE_TIME = 30 * 60 * 1000;

    /**
     *
     * @param token token
     * @param staffId 职工ID
     * @param secret 签名秘钥
     * @return 是否校验成功
     */
    public static Boolean verify(String token, Integer staffId, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("staffId", staffId).build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info("JWT Token校验成功，Params：====token:{}=====staffId:{}=====secret:{}=====", token, staffId, secret);
            return Boolean.TRUE;
        } catch (Exception exception) {
            log.error("JWT Token校验失败，Params：====token:{}=====staffId:{}=====secret:{}=====", token, staffId, secret);
            return Boolean.FALSE;
        }
    }

    /**
     * 生成token签名EXPIRE_TIME30分钟后过期
     *
     * @param staffId  职工ID
     * @param secret   签名秘钥
     * @return 加密的token
     */
    public static String generate(Integer staffId, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create().withClaim("staffId", staffId).withExpiresAt(date).sign(algorithm);
        log.info("JWT Token创建成功，Params: ====staffId:{}====secret:{}=====", staffId, secret);

        return token;
    }
}
