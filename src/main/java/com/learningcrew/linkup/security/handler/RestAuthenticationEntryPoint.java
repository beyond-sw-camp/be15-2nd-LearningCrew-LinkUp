package com.learningcrew.linkup.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningcrew.linkup.exception.security.CustomJwtException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.exception.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        /* jwtException 헤더로 exception 추가한 것 가져오기 */
        CustomJwtException jwtEx = (CustomJwtException) request.getAttribute("jwtException");

        /* ErrorResponse 생성 */
        ErrorResponse errorResponse = (jwtEx != null)
                ? ErrorResponse.of(jwtEx.getErrorCode())
                : ErrorResponse.of(ErrorCode.UNAUTHORIZED);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        /* ErrorResponse 직렬화후 에러 응답 전송 */
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
