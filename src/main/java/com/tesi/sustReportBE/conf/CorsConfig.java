package com.tesi.sustReportBE.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Abilita CORS su TUTTI gli endpoint
                .allowedOrigins("http://localhost:4200") // Permetti solo al frontend Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metodi permessi
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}