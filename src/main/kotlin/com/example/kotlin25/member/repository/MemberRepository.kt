package com.example.kotlin25.member.repository

import com.example.kotlin25.member.repository.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByNickname(nickname: String): Member?


}