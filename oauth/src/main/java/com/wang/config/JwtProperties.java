package com.wang.config;

import com.wang.util.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "yibu.auth.jwt")
public class JwtProperties {

    /**
     * 秘钥
     * */
//    @Value("${yibu.auth.jwt.secret}")
    private String secret;

//    @Value("${yibu.auth.jwt.pubkeyPath}")
    private String pubkeyPath;

//    @Value("${yibu.auth.jwt.prikeyPath}")
    private String prikeyPath;

//    @Value("${yibu.auth.jwt.expire}")
    private int expire;

//    @Value("${yibu.auth.jwt.cookieName}")
    private String cookieName;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @PostConstruct
    public void init(){
        try {
            File pubkey = new File(pubkeyPath);
            File prikey = new File(prikeyPath);
            if (!pubkey.exists() || !prikey.exists()){
                // 如果不存在生成公钥和私钥
                RsaUtils.generateKey(pubkeyPath,prikeyPath,secret);
            }
            // 读取公钥私钥
            this.publicKey = RsaUtils.getPublicKey(pubkeyPath);
            this.privateKey = RsaUtils.getPrivateKey(prikeyPath);
        }catch (Exception e){
            log.error("初始化公钥和私钥失败",e);
            throw new RuntimeException();
        }
    }

}
