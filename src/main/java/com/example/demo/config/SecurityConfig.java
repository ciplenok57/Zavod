package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers("/**").permitAll()
                .and()
                .csrf().disable()
                .cors();

        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // <-- Исправлено: добавляем слеш и две звездочки
                .allowedOrigins("*")  // Разрешить все источники
                .allowedMethods("*")  // Разрешить все методы (GET, POST, PUT, DELETE, и т.д.)
                .allowedHeaders("*")  // Разрешить все заголовки
                .maxAge(3600);        // Кеширование preflight запросов на 1 час
    }

}
