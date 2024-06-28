package com.example.kotlin25.post.dto

import jakarta.validation.constraints.Size

data class CreatePostRequest (
    @field:Size(max = 500, message = "제목은 최대 500자까지 입력 가능합니다.")
    val title: String,

    @field:Size(max = 5000, message = "작성 내용은 최대 5000자까지 입력 가능합니다.")
    val content: String,

    val nickname: String
)