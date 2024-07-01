package com.example.kotlin25.config.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin (
    @Value("\${auth.jwt.issuer}") private val issuer: String,

    @Value("\${auth.jwt.secret}") private val secret: String,

    @Value("\${auth.jwt.accessTokenExpiration}") private val accessTokenExpiration: Long
) {

    fun validateToken(jwt: String): Result<Jws<Claims>> {                           //jwt가 유효한지 검증하기 위해 사용
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8)) //비밀키를 바이트형태로 변환
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }


    fun generateAccessToken(subject: String, nickname: String): String {              //generateToken 함수 호출

        return generateToken(subject, nickname,  Duration.ofHours(accessTokenExpiration)) ////subject와 닉네임 기반으로 엑세스 토큰 생성 후 반환
    }

    private fun generateToken(subject: String,nickname: String,expirationPeriod: Duration): String {
        val claims: Claims = Jwts.claims() //JWT의 클레임(claim) 세트를 초기화
            .add(mapOf( "nickname" to nickname)) //사용자 지정 클레임을 추가 - 닉네임
            .build()


        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .claims(claims)
            .expiration(Date.from(now.plus(expirationPeriod)))
            .signWith(key)
            .compact()
    }
}