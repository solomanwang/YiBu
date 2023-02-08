package com.wang.util;

import com.alibaba.fastjson.JSON;
import com.wang.bean.UserInfo;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    public static final long EXPIRE = 1000 * 60 * 60;//token过期时间   24小时
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHOd2ds5f13s2d1fs5df13s";//密钥
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * 私钥加密token
     *
     * @param map           载荷中的数据
     * @param expireMinutes 过期时间，单位秒
     * @return
     * @throws Exception
     */
    public static String generateTokenRsa(Map<String, Object> map, PrivateKey key, int expireMinutes) throws Exception {
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(key, SignatureAlgorithm.RS256)
                .compact();
    }

    public static String generateTokenSecret(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setIssuer("wang")
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, APP_SECRET);
        if (ttlMillis >= 0L) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }

        return builder.compact();
    }

    /**
     * 公钥解析token
     *
     * @param token  用户请求中的token
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, PublicKey key) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    }
    /**
     *
     * 解析JWT字符串
     * @param jwt
     * @return
     */
    public static Claims parseJWTSecret(String jwt){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static UserInfo getUserInfo(HttpServletRequest request){
        String infoStr = StringUtils.substringAfter(request.getHeader(TOKEN_HEADER), TOKEN_PREFIX);
        return JSON.parseObject(infoStr,UserInfo.class);
    }

    /**
     * 从token中获取登录用户信息
     * @return userInfo json
     */
    public static String getUserInfoFromToken(String token) {
        String userInfo;
        try {
            Claims claims = parseJWTSecret(token);
            userInfo =  claims.getSubject();
        } catch (Exception e) {
            userInfo = null;
        }
        return userInfo;
    }
    /**
     * 获取token中的用户信息
     *
     * @param token  用户请求中的令牌
     * @return 用户信息
     * @throws Exception
     */
    public static Map<String, Object> getInfoFromToken(String token, PublicKey key) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, key);
        return claimsJws.getBody();
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
        Claims claims = parseJWTSecret(token);
        return claims == null ? null:claims.getExpiration();
    }

}