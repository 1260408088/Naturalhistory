package com.king.naturalhistory.config;

import com.king.naturalhistory.properties.JwtProperties;
import com.king.naturalhistory.utils.RsaUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties(prefix = "config.jwt")
@Component
@Data
public class JwtConfig {
    private JwtProperties jwtProperties;

    /*
     * 根据身份ID标识，生成Token
     */
    public String getToken(String identityId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(identityId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /*
     * 获取 Token 中注册信息
     */
    public Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(pubKeyPath)).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Token 是否过期验证
     */
    public boolean isTokenExpired(Date expirationTime) {
        return expirationTime.before(new Date());
    }

    private String secret;
    private long expire;
    private String pubKeyPath;
    private String header;
}

