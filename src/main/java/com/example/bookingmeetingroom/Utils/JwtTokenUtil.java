package com.example.bookingmeetingroom.Utils;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtTokenUtil {

    private static final String SECRET_KEY = "my_secret_key";

    public static String generateToken(String subject, long expirationMillis) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(subject) // Thông tin đối tượng
                .setIssuedAt(now) // Thời gian tạo token
                .setExpiration(expiration) // Thời gian hết hạn token
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Mã hóa bằng thuật toán HS256 và khóa bí mật
                .compact();
    }

    public static Claims decode(String token){
        validateToken(token);
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    public static String getSubject(String token){
        Claims claims = decode(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
    public static Date getExpireDate(String token){
        Claims claims = decode(token);
        if (claims != null) {
            return claims.getExpiration();
        }
        return null;
    }
    public static boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
        } catch (ExpiredJwtException ex) {
        } catch (UnsupportedJwtException ex) {
        } catch (IllegalArgumentException ex) {
        }
        return false;
    }
}