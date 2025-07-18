package com.example.rtilogin.security;

import com.example.rtilogin.entity.NetUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(NetUser user) {
        long expiration = 1000 * 60 * 60 * 24; // 24 hours
        return Jwts.builder()
            .setSubject(user.getUserName())
            .claim("userCode", user.getUserCode())
            .claim("userType", user.getUserType())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact();
            
    }

}