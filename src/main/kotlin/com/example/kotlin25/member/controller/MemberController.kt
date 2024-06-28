package com.example.kotlin25.member.controller

import com.example.kotlin25.member.dto.LoginRequest
import com.example.kotlin25.member.dto.LoginResponse
import com.example.kotlin25.member.dto.SignUpRequest
import com.example.kotlin25.member.repository.MemberRepository
import com.example.kotlin25.config.jwt.JwtCookieManager
import com.example.kotlin25.config.jwt.JwtPlugin
import com.example.kotlin25.member.service.MemberService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService,
    private val jwtPlugin: JwtPlugin,
    private val jwtCookieManager: JwtCookieManager,
    private val memberRepository: MemberRepository

){
    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody request: SignUpRequest
    ): ResponseEntity<String> {
        return ResponseEntity.ok(
            memberService.signUp(request)
        )
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest, response: HttpServletResponse
    ):ResponseEntity<LoginResponse>{
        val member = memberService.login(request) //사용자가 일치하면 토큰 생성
            jwtCookieManager.addJwtCookie(member.accessToken, response) //엑세스 토큰을 가지고 쿠키생성
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(member) //토큰반환
    }


}