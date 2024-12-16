package com.sfass.bsamonitoring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CORS 비활성화
        http.cors(AbstractHttpConfigurer::disable);

        // CSRF 비활성화
        http.csrf(AbstractHttpConfigurer::disable);

        // 모든 요청 허용 (테스트용)
        http.authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
        );

        return http.build();
    }
}
