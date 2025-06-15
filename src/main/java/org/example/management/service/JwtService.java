package org.example.management.service;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.example.management.model.request.UserRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey secret;

    public JwtService() {
        // Конструктор по умолчанию для инициализации
    }

    private void initializeSecret() {
        if (secret == null && jwtSecret != null) {
            byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
            this.secret = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
        }
    }

    /**
     * Генерация токена
     *
     * @param email
     * @param password
     * @return
     */
    public String generateToken(String email, String password) {
        initializeSecret();
        return Jwts.builder()
                .setSubject(email)
                .claim("email", email)
                .claim("password", password)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // Токен истекает через 1 час
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Получение email и password с token
     *
     * @param token JWT
     * @return Возвращает {@link UserRequest}
     */
    public UserRequest getEmailAndPassword(String token) {
        initializeSecret();
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        String email = claims.get("email", String.class);
        String password = claims.get("password", String.class);

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setPassword(password);
        return userRequest;
    }

    /**
     * Получение почты с токена
     *
     * @param token
     * @return
     */
    public String extractEmail(String token) {
        initializeSecret();
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Проверка на валидацию токена
     *
     * @param token
     * @return
     */
    public boolean isTokenValid(String token) {
        try {
            initializeSecret();
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
