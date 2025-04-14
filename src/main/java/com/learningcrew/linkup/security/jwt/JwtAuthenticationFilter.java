package com.learningcrew.linkup.security.jwt;

import com.learningcrew.linkup.exception.security.CustomJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);

        /* 토큰이 없으면 다음 필터로 넘긴다. */
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try{
            if(jwtTokenProvider.validateToken(token)) {
                // 토큰 Payload에서 Email 추출
                String userEmail = jwtTokenProvider.getEmailFromJWT(token);

                // 이메일 기반 사용자 정보 로드
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                // 인증 객체 생성
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                // SecurityContextHolder에 인증 객체 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch(CustomJwtException e){
            request.setAttribute("jwtException", e);
        }

        filterChain.doFilter(request, response);
    }

    /* HTTP Request 'Authorizatoin' value에서 JWT 토큰값만 추출 */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        /* bearer로 시작하는 유효한 토큰인 경우에만 추출 */
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
