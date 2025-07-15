/// 模板，生成token
package com.javaweb.todolist.util;// src/main/java/com/javaWeb/todolist/util/JwtUtil.java
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    // 1. 安全密钥（生产环境应从配置读取）
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("L17xiWangNiWeiLaiHaoHao-de06M1028".getBytes());///应当随机生成，但我选择她
    private static final long EXPIRATION_MS = 86400000; // 24小时

    // 2. 生成Token（包含用户ID）
    public static String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId) // 用户唯一标识
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS)) // 过期时间
                .signWith(SECRET_KEY) // 签名算法
                .compact();
    }

    // 3. 解析Token
    public static String parseToken(String token) throws JwtException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // 返回用户ID
    }
}