package com.buaa.review.util;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.Setter;

import java.util.Date;

@Setter
public class JwtUtils {

    //过期时间
    //public long ttl = 24 * 60 * 60 * 1000;
    public long ttl = 60 * 1000;

    //秘钥
    public String key = "littlebeeJwtKey";

    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJWT(String id, String subject, String roles) {
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .setAudience(roles)
                .signWith(SignatureAlgorithm.HS256, key);
        if (ttl > 0) {
            jwtBuilder.setExpiration(new Date(nowMills + ttl));
        }
        return jwtBuilder.compact();
    }


    /**
     * 解析JWT
     *
     * @param token
     * @return
     */
    public Claims parseJWT(String token) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key).
                            parseClaimsJws(token)
                    .getBody();
            return claims;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

    }

}