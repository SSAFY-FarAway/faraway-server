package com.ssafy.faraway.common.interceptor;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    public static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    private static final String HEADER_AUTH = "access-token";

//    @Autowired
    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        System.out.println(requestMethod);
        System.out.println(requestURI);
        System.out.println("@@@@헤더 리스트 출력");
        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = (String) headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " : " + value);
        }
        System.out.println("@@@@헤더 리스트 출력");
        // member 제외 모든 GET 요청 허용
        if (!requestURI.contains("member") && requestMethod.equals("GET")) {
            return true;
        }
        if(!requestURI.contains("member") && requestMethod.equals("OPTIONS")){
            String optionsMethod = request.getHeader("Access-Control-Request-Method");
            if(optionsMethod.equals("GET")){
                return true;
            }
        }


        final String token = request.getHeader(HEADER_AUTH);
        System.out.println(token);
        if (token != null && jwtService.checkToken(token)) {
            logger.info("토큰 사용 가능 : {}", token);
            return true;
        } else {
            logger.info("토큰 사용 불가능 : {}", token);
            throw new CustomException(ErrorCode.UNAUTHORIZED_ERROR);
        }
    }
}
