package com.tutorial.spring_boot_tutorial.jwt;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.tutorial.spring_boot_tutorial.main.vo.MainVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;
    private final long ACCESS_TOKEN_EXP_TIME;
    // private final long REFRESH_TOKEN_EXP_TIME;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.access-token-exp-time}") long accessTokenExpTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.ACCESS_TOKEN_EXP_TIME = accessTokenExpTime;
    }

    /**
     * Access Token 생성
     * 
     * @param member
     * @return Access Token String
     */
    public String createAccessToken(MainVo vo) {
        return createToken(vo, ACCESS_TOKEN_EXP_TIME);
    }


    /**
     * JWT 생성
     * 
     * @param member
     * @param expireTime
     * @return JWT String
     */
    private String createToken(MainVo member, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("name", member.getName());
        claims.put("grade", member.getGrade());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);


        return Jwts.builder().setClaims(claims).setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }


    /**
     * Token에서 User ID 추출
     * 
     * @param token
     * @return User ID
     */
    public Long getUserId(String token) {
        return parseClaims(token).get("memberId", Long.class);
    }


    /**
     * JWT 검증
     * 
     * @param token
     * @return IsValidate
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }


    /**
     * JWT Claims 추출
     * 
     * @param accessToken
     * @return JWT Claims
     */
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
