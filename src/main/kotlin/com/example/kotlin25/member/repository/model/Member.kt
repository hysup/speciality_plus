package com.example.kotlin25.member.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.crypto.password.PasswordEncoder

@Entity(name = "member")
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     var id: Long? = null,
     var password: String = "",
     var nickname: String = "",
     var role: MemberRole = MemberRole.USER
) {
    enum class MemberRole {
        USER, ADMIN
    }

    fun isCorrectPassword(passwordEncoder: PasswordEncoder,password: String): Boolean {
        return passwordEncoder.matches(password, this.password)
    }
}