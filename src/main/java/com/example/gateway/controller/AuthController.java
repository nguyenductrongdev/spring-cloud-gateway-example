package com.example.gateway.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final UUID fakeUserId = UUID.fromString("b6b6df01-b955-43ae-a8e5-063c8dc4ce95");
    @Value("${jwt.secret}")
    private String secret;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn() {
        String jwt = Jwts.builder()
                .setSubject("someone@something.com")
                .setIssuedAt(new Date())
                .claim("user_id", fakeUserId.toString())
                .setExpiration(new Date((new Date()).getTime() + Duration.ofMinutes(30).toMillis()))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return ResponseEntity.ok(Map.of("access_token", jwt));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestParam(name = "access_token") String accessToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(accessToken)
                .getBody();
        String userId = claims.get("user_id", String.class);
        return ResponseEntity.ok(Map.of("user_id", userId));
    }

}
