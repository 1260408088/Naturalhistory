package com.king.naturalhistory.properties;

import com.king.naturalhistory.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@Component()
@Slf4j
public class JwtProperties {

    @Value("${config.jwt.secret}")
    private String secret; // 密钥

    @Value("${config.jwt.pubKeyPath}")
    private String pubKeyPath;// 公钥

    @Value("${config.jwt.priKeyPath}")
    private String priKeyPath;// 私钥

    @Value("${config.jwt.cookieName}")
    private String cookieName;  //cookie的名称

    @Value("${config.jwt.expire}")
    private int expire;// token过期时间

    private PublicKey publicKey; // 公钥

    private PrivateKey privateKey; // 私钥

    /**
     * @PostContruct：在构造方法执行之后执行该方法
     */
    @PostConstruct
    public void init() {
        try {
            File pubKey = new File(pubKeyPath);
            File priKey = new File(priKeyPath);
            if (!pubKey.exists() || !priKey.exists()) {
                // 生成公钥和私钥
                RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
            }
            // 获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
        } catch (Exception e) {
            log.error("初始化公钥和私钥失败！", e);
            throw new RuntimeException();
        }
    }

}
