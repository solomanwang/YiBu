package com.yibu.getaway.config;

import com.wang.util.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@Data
@Slf4j
@Component
public class JwtProperties {

    @Value("${yibu.auth.jwt.pubkeyPath}")
    private String pubkeyPath;

    @Value("${yibu.auth.jwt.cookieName}")
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
