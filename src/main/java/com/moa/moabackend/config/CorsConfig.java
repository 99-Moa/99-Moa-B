package com.moa.moabackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:8080", "http://localhost:3000","http://18.206.140.108")
                .allowedMethods("*")
                .exposedHeaders("Access_Token","Refresh_Token")
                .allowCredentials(true)
                .maxAge(3000);
    }
}
