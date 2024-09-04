package com.tutorial.spring_boot_tutorial.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.access-token-exp-time}")
    private long ACCESS_TOKEN_EXP_TIME;

    @Value("${jwt.refresh-token-exp-time}")
    private long REFRESH_TOKEN_EXP_TIME;

    private Key key;

    // private final TokenMapper tokenMapper;
    // private final UserMapper userMapper;

    @Override // Bean이 생성되고 주입받은 후 SECRET_KEY값을 Base64 Decode해서 Key변수에 할당
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String userId) {
        Claims claims = Jwts.claims().setSubject(userId);

        Date now = new Date();
        Date accessValidity = new Date(now.getTime() + ACCESS_TOKEN_EXP_TIME);

        String accessToken = Jwts.builder().setClaims(claims) // user 정보
                .setIssuedAt(now).signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(accessValidity) // 만료시간 설정
                .compact();

        return accessToken;
    }

    public java.sql.Date time() {
        final long miliseconds = System.currentTimeMillis();
        return new java.sql.Date(miliseconds);
    }

    public String createRefreshToken(String userId) {

        Date now = new Date();
        System.out.println("now: " + now);

        Date refreshValidity = new Date(now.getTime() + REFRESH_TOKEN_EXP_TIME * 1000);

        String refreshToken = Jwts.builder().setSubject("").setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS256).setExpiration(refreshValidity).compact();

        java.sql.Date today = time();
        // int success = tokenMapper.insertToken(today, userId, refreshToken);

        return refreshToken;
    }

    // public Token checkRefresh(String refreshToken){
    // return tokenMapper.findToken(refreshToken);
    // }

    public String getUserIdFromJwt(String accessToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken)
                .getBody();

        return claims.getSubject();
    }

    // public UserDto checkUser(String accessToken){
    // String userId = getUserIdFromJwt(accessToken);
    // return userMapper.findByUserId(userId);
    // }

    public String getHeaderToken(String headerKey, HttpServletRequest request) {
        String bearerAccessToken = request.getHeader(headerKey);

        if (StringUtils.hasText(bearerAccessToken) && bearerAccessToken.startsWith("Bearer ")) {
            bearerAccessToken = bearerAccessToken.substring(7);
        }
        return bearerAccessToken;
    }

    public String getResHeaderAccessToken(String headerKey, HttpServletResponse response) {
        String bearerAccessToken = response.getHeader(headerKey);

        if (StringUtils.hasText(bearerAccessToken) && bearerAccessToken.startsWith("Bearer ")) {
            bearerAccessToken = bearerAccessToken.substring(7);
        }
        return bearerAccessToken;
    }

    public Map<Boolean, String> validateToken(String token) {
        Map<Boolean, String> result = new HashMap<>();
        try {
            Jws<Claims> claims =
                    Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            result.put(!claims.getBody().getExpiration().before(new Date()), "success");

            return result;

        } catch (ExpiredJwtException e) {
            System.out.println("만료된 JWT");
            result.put(true, "fail");

            return result;

        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            System.out.println("잘못된 JWT 서명");
        } catch (UnsupportedJwtException e) {
            System.out.println("지원되지 않는 JWT");
        } catch (IllegalStateException e) {
            System.out.println("JWT 토큰 잘못됨");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT 토큰 없음");
        }

        result.put(false, "유호하지 않음");

        return result;
    }

    // public String deleteToken(String refreshToken) {
    // int result = tokenMapper.deleteToken(refreshToken);

    // if (result > 0) {
    // return "success";
    // }
    // return "fail";
    // }
}
