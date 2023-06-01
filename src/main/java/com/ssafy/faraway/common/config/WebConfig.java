package com.ssafy.faraway.common.config;

import com.ssafy.faraway.common.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpMethod.*;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/",
//                        "/member",
                        "/member/check/**", // 아이디 중복 검사
                        "/member/check", // 비밀번호 확인
                        "/member/login", // 로그인
                        "/member/info/**",
                        "/member/login-pwd", // 비밀번호 초기화
                        "/member/login-id", // 아이디 찾기
                        "/member/sign-up", // 회원가입
                        "/member/refresh",
                        "/member/logout/**",
//                       -----Member------
//                        "/attraction/**", // 시도,구군
//                        "/attraction", // searchAttractions
//                       -----Attraction------
//                        "/hot-place/**", // 조회, 상세조회 , update, delete도 포함됨!! url 수정 필요
//                       -----Hotplace------
//                        "/plan/**", // 조회, 상세조회 , update, delete도 포함됨
//                       -----Plan------
//                        "/post/**", // 조회, 상세조회 , update, delete도 포함됨
//                       -----Post------
//                        "/search_place",
//                        "/plan-list",
//                        "/hotplace-list",
//                        "/post-list",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/resources/static/**",
                        "/v2/api-docs"
//                        "/plan-view/**",
//                        "/plan/**"
                );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080")
//                .allowedMethods("*");
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정 적용
                .allowedOrigins("http://3.34.253.86/") // 허용할 Origin
//                .allowedOrigins("*") // 허용할 Origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 허용할 헤더
                .allowCredentials(true); // 자격 증명 허용 여부
    }
}
