package com.example.kotlin25.comment.repository

import com.example.kotlin25.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}