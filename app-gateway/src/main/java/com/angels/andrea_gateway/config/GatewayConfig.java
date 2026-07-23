package com.angels.andrea_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${services.auth-api.uri:http://localhost:8080}")
    private String authApiUri;

    @Value("${services.resource-api.uri:http://localhost:8081}")
    private String resourceApiUri;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder,
                               IpKeyResolver keyResolver,
                               RedisRateLimiter rateLimiter) {

        return builder.routes()

                // hermecard-auth-api-server: OAuth2 (authorize/token), login form,
                // cadastro de usuário e callback do fluxo de autorização
                .route("auth-api", route -> route
                        .path(
                                "/oauth2/**",
                                "/.well-known/**",
                                "/login/**",
                                "/logout",
                                "/users",
                                "/callback",
                                "/failure"
                        )
                        .filters(filter -> filter
                                .requestRateLimiter(config -> config
                                        .setKeyResolver(keyResolver)
                                        .setRateLimiter(rateLimiter))
                        )
                        .uri(authApiUri)
                )

                // hermecard-resource-api: endpoints protegidos por JWT (CRM) e de teste
                .route("resource-api", route -> route
                        .path(
                                "/api/leads/**",
                                "/api/deals/**",
                                "/api/activities/**",
                                "/api/clients/**",   // <-- Novo endpoint
                                "/api/client-tasks/**",
                                "/api/leads/**",
                                "/api/lead-tasks/**",
                                "/public/**",
                                "/privado/**"
                        )
                        .filters(filter -> filter
                                .requestRateLimiter(config -> config
                                        .setKeyResolver(keyResolver)
                                        .setRateLimiter(rateLimiter))
                        )
                        .uri(resourceApiUri)
                )

                .build();
    }
}