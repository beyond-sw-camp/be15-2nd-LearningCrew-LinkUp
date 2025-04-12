package com.learningcrew.linkup.config;

import com.learningcrew.linkup.security.jwt.JwtAuthenticationFilter;
import com.learningcrew.linkup.security.jwt.JwtTokenProvider;
import com.learningcrew.linkup.security.handler.RestAccessDeniedHandler;
import com.learningcrew.linkup.security.handler.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity //컨트롤 메서드 제어 가능하도록 활성화
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception ->
                        exception
                                .authenticationEntryPoint(restAuthenticationEntryPoint)     // 인증 실패
                                .accessDeniedHandler(restAccessDeniedHandler)          // 인가 실패
                )
                // 요청 http method, url 기준으로 인증, 인가 필요 여부 설정
                .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/api/v1/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                                .requestMatchers("/api/v1/users/register", "/api/v1/auth/login", "/api/v1/auth/refresh", "/api/v1/auth/verify-email").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAuthority("USER") //추후에 hasRole로 수정
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAuthority("USER")
//                                .requestMatchers(HttpMethod.GET, "/api/v1/admin/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
                // 커스텀 인증 필터(jwt 토큰 필터)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService);
    }

}