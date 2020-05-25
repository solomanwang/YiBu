package com.yibu.auth;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.wang.util.JwtUtils;
import com.wang.util.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@SpringBootTest
public class JwtTest {
    private static final String pubKeyPath = "D:\\rsa\\yibu\\rsa.pub";

    private static final String priKeyPath = "D:\\rsa\\yibu\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "sf3423jsdf#3$@FDS32");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "1");
        map.put("username", "wzq");
        // 生成token
        String token = JwtUtils.generateToken(map, privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MiwidXNlcm5hbWUiOiLmnY7lm5siLCJleHAiOjE1OTAyMTM1NjF9.OXfrVb0Li6GKmyaFhIIDh5bcTFHOmxKrjmtAU6eqhdA9CxPREf4QzCEAGv6sD1mi55bWTO54TSFznVxJsmc78yIJy0H-VlCb75lqM0Vu_7yfZM0FJj3uSIbPtPPH00hOPHRabUzRMoUeMAv8POEX8OdgBTDhnFVZzeDjAk0d0pYWvNpUpWIidQRqu_bbim6ZcobgL7IVQnaDlofof0VC6viHzR4wb19-SdsUo2DKEHUHZjn3zLbZFGds_F_s9i6CPEqKHueN7aXcL8xNqixlZSXEbYT1de-BnFV1EDmAB9HGdInuGfTX2Wa1gLTIuTeirur37OmmDkpVNMKUEG-ZNw";

        // 解析token
        Map<String, Object> map = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + map.get("id"));
        System.out.println("userName: " + map.get("username"));
    }
    @Test
    public void test(){
        DateTime x = DateUtil.parseDate("1987-01-01");
        System.out.println(x.getTime());
    }
}
