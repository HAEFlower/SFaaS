package com.sfass.bsamonitoring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("*");
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // 모든 엔드포인트에 적용
//                        .allowedOrigins("*") // 모든 도메인 허용
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용 메서드
//                        .allowedHeaders("*") // 모든 헤더 허용
//                        .allowCredentials(false) // 인증 정보 비허용 (필요 시 true로 설정)
//                        .maxAge(3600); // 캐싱 시간 (초 단위)
//            }
//        };
//    }
}
