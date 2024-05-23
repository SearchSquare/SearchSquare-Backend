package com.searchsquare.core.util;


import com.searchsquare.core.exception.ExpiredJwtException;
import com.searchsquare.core.exception.UnAuthorizedException;
import com.searchsquare.core.exception.UnsupportedJwtException;
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

@Component
@Slf4j
public class JwtUtil {

    private final String BEARER = "Bearer ";
    private final String ACCESS_TOKEN = "access-token";
    private final String MEMBER_ID = "member-id";

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.access-token.expiretime}")
    private long accessTokenExpireTime;

    public String createAccessToken(String memberId) {
        return create(memberId, ACCESS_TOKEN, accessTokenExpireTime);
    }

    private String create(String memberId, String subject, long expireTime) {
        return BEARER + Jwts.builder()
            .setSubject(subject)
            .setIssuer(issuer)
            .claim(MEMBER_ID, memberId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expireTime))
            .signWith(Keys.hmacShaKeyFor(generateKey()), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean checkToken(String token) {
        Jws<Claims> claims = getClaims(token);
        /* 유효하지 않는 경우 */
        if (isExpired(claims)) {
            throw new ExpiredJwtException();
        }
        /* 발행자가 일치하지 않는 경우 */
        if (isDiffIssuer(claims)) {
            throw new UnsupportedJwtException();
        }
        return true;
    }

    public String getMemberId(String token) {
        Jws<Claims> claims = null;
        try {
            claims = getClaims(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UnAuthorizedException();
        }
        return claims.getBody().get(MEMBER_ID, String.class);
    }

    private boolean isDiffIssuer(Jws<Claims> claims) {
        return !claims.getBody().getIssuer().equals(issuer);
    }

    private static boolean isExpired(Jws<Claims> claims) {
        return claims.getBody().getExpiration().before(new Date());
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = salt.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if (log.isInfoEnabled()) {
                e.printStackTrace();
            } else {
                log.error("Making JWT Key Error {}", e.getMessage());
            }
        }
        return key;
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(generateKey()))
            .build()
            .parseClaimsJws(token.replace(BEARER, ""));
    }
}
