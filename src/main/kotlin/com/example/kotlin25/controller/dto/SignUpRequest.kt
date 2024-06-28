package com.example.kotlin25.controller.dto

data class SignUpRequest (
    val nickname: String,
    val password: String,
    val passwordConfirm: String
)