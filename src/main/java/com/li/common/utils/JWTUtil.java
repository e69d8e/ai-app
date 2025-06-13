package com.li.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class JWTUtil {
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 指定签名的时候使用的签名算法，也就是header那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // 设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置过期时间
                .setExpiration(exp);

        return builder.compact();
    }
    // 解析
    public static Claims parseJWT(String secretKey, String token) {
        // 得到DefaultJwtParser
        // 设置签名的秘钥
        // 设置需要解析的jwt
        return Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
    }


    public static void main(String[] args) {
        String secretKey = "token";
        long ttlMillis = 3600000;
        Map<String, Object> claims = new TreeMap<>();
        claims.put("userId", "123");
        claims.put("username", "John Doe");
        String token = createJWT(secretKey, ttlMillis, claims);
        System.out.println("Generated Token: " + token);
        Claims parsedClaims = parseJWT(secretKey, token);
        System.out.println("Parsed Claims: " + parsedClaims);
    }
}
