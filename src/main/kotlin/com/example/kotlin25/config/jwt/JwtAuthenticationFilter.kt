package com.example.kotlin25.config.jwt


import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(private val jwtPlugin: JwtPlugin) : OncePerRequestFilter() {

    companion object {
        private val BEARER_PATTERN = Regex("^Bearer (.+)$") //문자열이 "Bearer <토큰값>" 형식인지를 검사하기 위한 정규식 패턴
    }
    override fun doFilterInternal(
        request: HttpServletRequest, //HTTP 요청에 대한 정보를 제공하는 객체이며 요청 URI, 헤더가 있음
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if (jwt != null) {
            jwtPlugin.validateToken(jwt)
                .onSuccess {
                    val userId = it.payload.subject.toLong()
                    val nickname = it.payload.get("nickname", String::class.java)

                    val principal = com.example.kotlin25.config.sercurity.UserPrincipal(
                        id = userId,
                        nickname = nickname,
                    )
                    val authentication = JwtAuthenticationToken( //인증토큰
                        principal = principal,
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    )

                    SecurityContextHolder.getContext().authentication = authentication //인증된 사용자 정보설정
                }
        }
        filterChain.doFilter(request, response) // 다음 필터 호출
    }

    private fun HttpServletRequest.getBearerToken(): String? {
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
    }
}



