package com.example.kotlin25.config.aop

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

//@Aspect
//@Component
//class SecurityAspect {
//    @Before(
//        "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
//            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
//            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)"
//    )
//    fun checkAuthorization() { //메서드 실행 전에 인증된 사용자인지 확인
//        val authentication = SecurityContextHolder.getContext().authentication //현재 사용자의 인증 정보를 가져옴
//        if (authentication == null || !authentication.isAuthenticated) {
//            throw AccessDeniedException("Access is denied. User not authenticated.") //사용자인증이 안되거나 권한이 없는 경우 예외던짐
//        }
//    }
//}