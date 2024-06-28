package com.example.kotlin25.member.service

import com.example.kotlin25.member.dto.LoginRequest
import com.example.kotlin25.member.dto.LoginResponse
import com.example.kotlin25.member.dto.SignUpRequest
import com.example.kotlin25.member.repository.MemberRepository
import com.example.kotlin25.member.repository.model.Member
import com.example.kotlin25.config.jwt.JwtPlugin
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) {

    private fun validate(signUpRequest: SignUpRequest)  {

        //닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성
        if (!signUpRequest.nickname.matches(Regex("[a-zA-Z0-9]{3,}")))
            throw IllegalArgumentException("닉네임은 최소 3자 이상의 알파벳 대소문자와 숫자로 구성되어야 합니다.")

        // 비밀번호 유효성 검사
        if (signUpRequest.password.length < 4)
            throw IllegalArgumentException("비밀번호는 최소 4자 이상이어야 합니다.")

        // 닉네임과 비밀번호 중복 검사
        if (signUpRequest.password.contains(signUpRequest.nickname))
            throw IllegalArgumentException("비밀번호에 닉네임과 동일한 값이 포함될 수 없습니다.")


        // 비밀번호 일치 검사
        if (signUpRequest.password != signUpRequest.passwordConfirm)
            throw IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.")


        // 데이터베이스에 존재하는 닉네임인지 확인
        if (memberRepository.findByNickname(signUpRequest.nickname) != null)
            throw IllegalArgumentException("중복된 닉네임입니다.")

    }
    fun signUp(signUpRequest: SignUpRequest): String {
        validate(signUpRequest)

        val encodedPassword = passwordEncoder.encode(signUpRequest.password) //객체에서 받은 비밀번호를 암호화
        val member = Member(
            password = encodedPassword,
            nickname = signUpRequest.nickname // 변수 생성
        )
        memberRepository.save(member) // 회원정보를 저장
        return "회원가입 성공!" // 가입이 되었음!!
    }

    fun login(request: LoginRequest): LoginResponse {

        val nickname = request.nickname
        val member = memberRepository.findByNickname(nickname) ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다")

        // 닉네임으로 사용자 정보 조회

        if (!passwordEncoder.matches(request.password, member.password)) {
            throw IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요")
        }
        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                nickname = nickname,
                subject = member.id.toString()
            )
        )


    }
}