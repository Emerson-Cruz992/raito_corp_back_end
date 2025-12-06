package com.projetoIntegrador.RaitoCorp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permitir credenciais
        config.setAllowCredentials(true);

        // Permitir origens
        config.setAllowedOrigins(Arrays.asList(
            "http://localhost",
            "http://localhost:80",
            "http://localhost:4200",
            "http://127.0.0.1",
            "http://127.0.0.1:80",
            "http://127.0.0.1:4200"
        ));

        // Permitir todos os headers
        config.addAllowedHeader("*");

        // Permitir todos os métodos
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

        // Expor headers de resposta
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
