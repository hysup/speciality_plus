package com.example.kotlin25.config.jwt

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component

@Component
class JwtCookieManager {

    fun addJwtCookie(generateToken: String, res: HttpServletResponse) {
        val cookie = Cookie("jwt", generateToken)
        cookie.path = "/"
        cookie.isHttpOnly = true //javascript 쿠키 값에 접근하는 것을 막아줌
        res.addCookie(cookie)
    }
}