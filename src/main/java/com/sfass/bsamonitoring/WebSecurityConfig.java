package com.sfass.bsamonitoring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        // CORS 비활성화
        http.cors(cors -> cors.configurationSource(request ->
        {
            var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
            corsConfiguration.addAllowedOriginPattern("*");
            corsConfiguration.addAllowedMethod("*");
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.setAllowCredentials(true);
            return corsConfiguration;
        }))
                .csrf(csrf -> csrf.disable())

        // 모든 요청 허용 (테스트용)
        .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
        );

        return http.build();
    }
}
