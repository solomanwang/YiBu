package com.yibu.goods.config;

import com.wang.util.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author wzq
 * @date 2020/5/25
 * @DESC
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "yibu.auth.jwt")
public class JwtProperties {

    private String pubkeyPath;

    private String cookieName;

    private PublicKey publicKey;

    @PostConstruct
    public void init(){
        try {
            // 读取公钥私钥
            this.publicKey = RsaUtils.getPublicKey(pubkeyPath);
        }catch (Exception e){
            log.error("初始化公钥和私钥失败",e);
            throw new RuntimeException();
        }
    }
}
