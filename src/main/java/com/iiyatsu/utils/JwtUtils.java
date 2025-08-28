package com.iiyatsu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JwtUtils {
    // 使用一个固定的、更安全的密钥字符串
    // 密钥长度应至少为32个字符，以获得更高的安全性。
    private static final String SECRET_STRING = "your_super_secure_secret_key_for_jwt_2023_museum_project_with_at_least_32_characters";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    // 令牌过期时间：1 小时
    private static final long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(1);

    /**
     * 根据给定的数据生成 JWT 令牌。
     *
     * @param claims 包含在令牌中的自定义数据（如用户ID、角色等）。
     * @return 生成的 JWT 字符串。
     */
    public static String generateToken(Map<String, Object> claims) {
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 JWT 令牌并返回其中的数据（claims）。
     *
     * @param token 需要解析的 JWT 字符串。
     * @return 包含在令牌中的数据（Claims 对象）。
     */
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}