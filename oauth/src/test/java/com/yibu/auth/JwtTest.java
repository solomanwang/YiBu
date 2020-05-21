package com.yibu.auth;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.wang.util.DateUtils;
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
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
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
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6NCwidXNlcm5hbWUiOiLlpKfmmI4iLCJleHAiOjE1OTAwNTQ3MDV9.O4URRlu_mciohYgCET_oOybUqZEi_m2hv7JnJf6b-zAsOl2FVUIK1MycLm1SItd_memJWU8XMVY-Jm7_hHAjFWDLoXUiI9lR7OTg8-HsqNQyB03Ds_fPURycoTIYJ5DY01z1M5-e_9nmJDj_apTKG4iIsmb9kf5pexeCFTtuidSd0P2JfQ-AuDprqAxU8A9PR9NmzGuc92byO7vP60T5YTjZzl4daDSaO51Goii45TKmxEpsgl3D9k2_JHcyvBX36MRrbG8MtprM8zYfn0GDYYOGg_9stXZYh04ALDn0HKQTvzJ2mIWGjyHlxRQ8Kyda7wtaLQIBcBUWa3mtbckwCw";

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
