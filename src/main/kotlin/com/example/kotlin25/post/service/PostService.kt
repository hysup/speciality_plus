package com.example.kotlin25.post.service

import com.example.kotlin25.config.sercurity.UserPrincipal
import com.example.kotlin25.global.Type.ModelNotFoundException
import com.example.kotlin25.post.dto.CreatePostRequest
import com.example.kotlin25.post.dto.PostResponse
import com.example.kotlin25.post.dto.UpdatePostRequest
import com.example.kotlin25.post.model.Post
import com.example.kotlin25.post.repository.PostRepository
import com.example.kotlin25.member.repository.MemberRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

import java.time.ZonedDateTime


@Service
class PostService (
    private val postRepository: PostRepository,
    private val memRepository: MemberRepository,
    ) {

    fun createPost(request: CreatePostRequest): PostResponse {

        val authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal as UserPrincipal
        val member = memRepository.findByNickname(principal.nickname)
            ?: throw ModelNotFoundException("Member")
        val post = Post(
            title = request.title,
            content = request.content,
            createdAt = ZonedDateTime.now(),
            nickname = request.nickname,

        )
        val savedPost = postRepository.save(post)
        return savedPost.toResponse()

    }

    fun getAllPosts(): List<PostResponse> {

        return postRepository.findAllByOrderByCreatedAtDesc().map { it.toResponse() }
    }

    fun getPost(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId)?: throw EntityNotFoundException("Post not found with id $postId")

        return post.toResponseWithComment()
    }

    fun updatePost(postId:Long, request: UpdatePostRequest): PostResponse {
        val authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal as UserPrincipal
        val member = memRepository.findByNickname(principal.nickname)
            ?: throw ModelNotFoundException("Member")

        var post = postRepository.findByIdOrNull(postId)?: throw EntityNotFoundException("Post not found with id $postId")
        post.title = request.title
        post.content = request.content

        val updatedPost = postRepository.save(post)
        return updatedPost.toResponse()

    }

    fun deletePost(postId:Long) {
        val authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal as UserPrincipal
        val member = memRepository.findByNickname(principal.nickname)
        postRepository.deleteAll()
    }

}

