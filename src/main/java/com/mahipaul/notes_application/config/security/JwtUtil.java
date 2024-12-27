package com.mahipaul.notes_application.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "d25e288e25fb7c595d9a1ab6bbc249bdecee39955dfe72ecebc9c43cff8b8dfa9145a19454c55e12b84e5000801a2b505570da06330a472b840414ae04ce49d5a4ba22344269a3e80d299f3f11cd01e7a9cde58186293854e57c31c25e78e0ef54b02d66dd87075748848b95a9a2f5a6da782f9c785211304016a9b205d7c9bec8881f6d1cf5c9469eb95d2435c1b5379741591d9b204be06af8608203b24056aa076383ac54a1ecd016861eb7c88ab0a89c2e659b4acf567c35703bc9049de31d2a8ce9df76da662f39182fddc741e84162cd275f5de10f4e99f09f40defbaa07033fc44b0073119b1387848b03011e654312ebfe365d3ef7fdbde32b3b5695";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }
}