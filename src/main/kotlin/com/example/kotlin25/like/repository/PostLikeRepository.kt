package com.example.kotlin25.like.repository

import com.example.kotlin25.like.model.Like
import org.springframework.data.jpa.repository.JpaRepository

interface PostLikeRepository: JpaRepository<Like, Long> {
}