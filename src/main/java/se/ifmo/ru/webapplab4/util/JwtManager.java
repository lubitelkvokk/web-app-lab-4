package se.ifmo.ru.webapplab4.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.Date;

@Named
@ApplicationScoped
public class JwtManager {

    // TODO put into config file or environment variable
        private final String SECRET_KEY = "HI23knNfi29dMNTobGabfFaccident"; // Замените на ваш секретный ключ

        // Метод для создания JWT токена
        public String createToken(String subject) {
            Date now = new Date();
            Date expirationDate = new Date(now.getTime() + 3600000); // Токен будет действителен 1 час

            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(now)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        }

        // Метод для верификации JWT токена
        public boolean verifyToken(String token) {
            try {
                Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
                return true; // Подпись верна и токен не истек
            } catch (Exception e) {
                return false; // Ошибка при верификации токена
            }
        }

        // Метод для извлечения данных из JWT токена
        public String extractSubject(String token) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims.getSubject();
        }
}
