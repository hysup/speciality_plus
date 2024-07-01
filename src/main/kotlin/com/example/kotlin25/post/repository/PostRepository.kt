package com.example.kotlin25.post.repository

import com.example.kotlin25.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>,CustomPostRepository {
    fun findAllByOrderByCreatedAtDesc(): List<Post>


}