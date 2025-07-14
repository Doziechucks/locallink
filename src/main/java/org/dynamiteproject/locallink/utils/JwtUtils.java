package org.dynamiteproject.locallink.utils;

import org.dynamiteproject.locallink.data.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private final SecretKey SECRET_KEY;
    private static final String ISSUER = "locallink";
    private final long EXPIRATION_TIME_MS;


    public JwtUtils(
            @Value("${jwt.secret}") String base64Secret,
            @Value("${jwt.expiration.ms}") long expirationTimeMs
    ) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Secret));
        this.EXPIRATION_TIME_MS = expirationTimeMs;
        }


    public String generateToken(String userId, String firstName, String email, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", firstName);
        claims.put("email", email);
        claims.put("role", role.name());

        return createToken(userId, claims);
        }

    private String createToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(subject)// New charset-safe method
                .issuer(ISSUER)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .claims(claims)
                .signWith(SECRET_KEY) // SignatureAlgorithm inferred from key type
                .compact();
        }

    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
        }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
        }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
        }

    public String extractFirstName(String token) {
        return extractClaim(token, claims -> claims.get("firstName", String.class));
        }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
        }

    public Role extractRole(String token) {
        String role = extractClaim(token, claims -> claims.get("role", String.class));
        return Role.valueOf(role);
        }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(token);
        return claimsResolver.apply(claims);
        }
}
