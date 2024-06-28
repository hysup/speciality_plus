package com.example.kotlin25.config.jwt

import com.example.kotlin25.config.sercurity.UserPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails
import java.io.Serializable

class JwtAuthenticationToken( //인증 토큰을 나타냄
    private val principal: UserPrincipal,
    details: WebAuthenticationDetails, //인증에 필요한 세부정보
): AbstractAuthenticationToken(principal.authorities), Serializable {

    init{
        super.setAuthenticated(true) //인증 상태 설정
        super.setDetails(details) //추가 세부정보 설정
    }

    override fun getCredentials() =null //자격증명


    override fun getPrincipal() = principal

    override fun isAuthenticated(): Boolean { //인증되었는지 반환하는 메서드
        return true

    }
}