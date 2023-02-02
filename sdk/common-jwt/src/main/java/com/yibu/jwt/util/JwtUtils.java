package com.yibu.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @ClassName : JwtUtils
 * @Description :
 * @Author : wzq
 * @Date: 2023-01-11 15:26
 */
public class JwtUtils {
    private static final String JWT_PAYLOAD_USER_KEY = "user";
//    public static final long EXPIRE = 1000 * 60 * 60 * 24;//token过期时间   24小时
    public static final long EXPIRE = 1000 * 60 * 60;//token过期时间   24小时
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHOd2ds5f13s2d1fs5df13s";//密钥
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * 签发JWT
     * @param id
     * @param subject
     * @param expDate 过期时刻
     * @return
     */
    public static String createJWT(String id, String subject, Date expDate) {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .setIssuer("wang")
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET);
        builder.setExpiration(expDate);
        return builder.compact();
    }
    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setIssuer("wang").setId(id).setSubject(subject).setIssuedAt(now).signWith(signatureAlgorithm, APP_SECRET);
        if (ttlMillis >= 0L) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }

        return builder.compact();
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String username = getUserNameFromToken(token);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }

    /**
     * 从token中获取登录用户信息
     * @return userInfo json
     */
    public String getUserInfoFromToken(String token) {
        String userInfo;
        try {
            Claims claims = parseJWT(token);
            userInfo =  claims.getSubject();
        } catch (Exception e) {
            userInfo = null;
        }
        return userInfo;
    }

    /**
     *
     * 解析JWT字符串
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){
        Claims claims = null;
        try {

            claims = Jwts.parser().setSigningKey(APP_SECRET)
                    .parseClaimsJws(jwt).getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }

    public static void main(String[] args) {
        String jwt = createJWT("1", "{\n" +
                "    \"code\": \"401\",\n" +
                "    \"message\": \"未认证\",\n" +
                "    \"success\": false\n" +
                "}", 1000L * 10);
        System.out.println(jwt);
//        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJwcm1zIiwianRpIjoiMSIsInN1YiI6IntcbiAgICBcImNvZGVcIjogXCI0MDFcIixcbiAgICBcIm1lc3NhZ2VcIjogXCLmnKrorqTor4FcIixcbiAgICBcInN1Y2Nlc3NcIjogZmFsc2Vcbn0iLCJpYXQiOjE2NzM0ODYxMjgsImV4cCI6MTY3MzQ4NjEzOH0.ZuTBD0u9t-xwU81Mv1CZJ4LQK4UCvwqrARxp8Uq5bsc";
            boolean claims = isTokenExpired(jwt);
            System.out.println("token是否过期："+claims);
    }

    /**
     * 判断token是否已经失效
     */
    private static boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        boolean flag = true;
        if (null == expiredDate) return flag;
        return expiredDate.before(new Date());
    }
    /**
     * 从token中获取过期时间
     */
    private static Date getExpiredDateFromToken(String token) {
        Claims claims = parseJWT(token);
        return claims == null ? null:claims.getExpiration();
    }

    private JwtUtils() {

    }

}
