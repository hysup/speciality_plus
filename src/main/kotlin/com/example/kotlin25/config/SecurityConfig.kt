package com.example.kotlin25.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
) {
    @Bean
    fun filterChain(http: HttpSecurity,introspector: HandlerMappingIntrospector): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .headers { it.frameOptions().sameOrigin() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/api/v1/member/login",
                    "/api/v1/member/sign-up",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/h2-console/**",
                ).permitAll()
                    // 위 URI를 제외하곤 모두 인증이 되어야 함.
                    .requestMatchers(
                        MvcRequestMatcher(introspector, "/**")
                        .apply { setServletPath("/h2-console") }).permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

            .build()
    }
}