package com.example.kotlin25.post.repository

import com.example.kotlin25.post.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomPostRepository {
    fun searchByPostListByTitle(title:String):List<Post>

    fun findByPageable(pageable: Pageable):Page<Post>
}