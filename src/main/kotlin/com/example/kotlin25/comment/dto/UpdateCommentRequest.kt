package com.example.kotlin25.comment.dto

import jakarta.validation.constraints.NotBlank

data class UpdateCommentRequest(
    @field:NotBlank
    val content: String
)
