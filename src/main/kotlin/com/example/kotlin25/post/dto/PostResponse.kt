package com.example.kotlin25.post.dto

import com.example.kotlin25.comment.dto.CommentResponse
import java.time.ZonedDateTime

data class PostResponse(
    val title: String,
    val nickname: String,
    val createdAt: ZonedDateTime,
    val content: String,
    val id: Long?,
    val comments: List<CommentResponse>
)
