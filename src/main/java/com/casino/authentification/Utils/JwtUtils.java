package com.casino.authentification.Utils;

import com.casino.authentification.Models.Entities.Player;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtils {

  private static final String SECRET_KEY = "c29tZXJhbmRvbGJhc2U2NGVuY29kZWRrZXk=";

  private static SecretKey getSignInKey() {
    byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  // Méthode pour générer un token JWT
  public static String generateToken(String username) {
    return Jwts
        .builder()
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        // the token will be expired in 10 hours
        .expiration(new Date(System.currentTimeMillis() + 1000* 60 * 60 *10))
        .signWith(getSignInKey(), Jwts.SIG.HS256)
        .compact();
  }

  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public static void encodePassword(Player player) {
    player.setPassword(passwordEncoder().encode(player.getPassword()));
  }
}
