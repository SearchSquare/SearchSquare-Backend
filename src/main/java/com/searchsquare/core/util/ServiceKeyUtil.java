package com.searchsquare.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceKeyUtil {

    private final String DAILY_LIMIT = "daily-limit";

    @Value("${service-key.issuer}")
    private String issuer;

    @Value("${service-key.salt}")
    private String salt;

    @Value("${service-key.expire-time}")
    private long expireTime;

    public String createServiceKey(int dailyLimit) {
        return create(issuer, salt, expireTime, dailyLimit);
    }

    private String create(String issuer, String salt, long expireTime, int dailyLimit) {
        return Jwts.builder()
            .setIssuer(issuer)
            .claim(DAILY_LIMIT, dailyLimit)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expireTime))
            .signWith(Keys.hmacShaKeyFor(generateKey()), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean checkServiceKey(String serviceKey) {
        Jws<Claims> claims = getClaims(serviceKey);
        if (isExpired(claims)) {
            return false;
        }
        if (isNotEqualIssuer(claims)) {
            return false;
        }
        return true;
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = salt.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if (log.isInfoEnabled()) {
                e.printStackTrace();
            } else {
                log.error("Making ServiceKey Error {}", e.getMessage());
            }
        }
        return key;
    }

    private Jws<Claims> getClaims(String serviceKey) {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(generateKey()))
            .build()
            .parseClaimsJws(serviceKey);
    }

    private boolean isNotEqualIssuer(Jws<Claims> claims) {
        return !claims.getBody().getIssuer().equals(issuer);
    }

    private boolean isExpired(Jws<Claims> claims) {
        return claims.getBody().getExpiration().before(new Date());
    }
}
