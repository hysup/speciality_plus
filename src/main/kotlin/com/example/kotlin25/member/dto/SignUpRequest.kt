package com.example.kotlin25.member.dto

data class SignUpRequest (
    val nickname: String,
    val password: String,
    val passwordConfirm: String
)