package com.example.kotlin25.comment.dto

import java.time.ZonedDateTime

data class CommentResponse(
    val id: Long,
    val postId: Long,
    val content: String,
    val createdAt: ZonedDateTime,
)
