package com.example.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RestAuthFilter implements GlobalFilter {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        try {
            String beaverToken = exchange.getRequest().getHeaders().getFirst("Authorization");
            String jwtToken = beaverToken.replace("Bearer ", "");

            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwtToken)
                    .getBody();

            String userId = claims.get("user_id", String.class);

            ServerHttpRequest mutated = request.mutate()
                    .header("X-User-Id", userId)
                    .build();

            return chain.filter(exchange.mutate().request(mutated).build());

        } catch (Exception e) {
            return chain.filter(exchange);
        }
    }
}