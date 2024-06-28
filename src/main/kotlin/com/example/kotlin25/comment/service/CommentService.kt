package com.example.kotlin25.comment.service

import com.example.kotlin25.comment.dto.CommentResponse
import com.example.kotlin25.comment.dto.CreateCommentRequest
import com.example.kotlin25.comment.dto.UpdateCommentRequest
import com.example.kotlin25.comment.model.Comment
import com.example.kotlin25.comment.repository.CommentRepository
import com.example.kotlin25.config.sercurity.UserPrincipal
import com.example.kotlin25.global.Type.ModelNotFoundException
import com.example.kotlin25.post.repository.PostRepository
import com.example.kotlin25.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository

    ) {
    @Transactional
    fun createComment(
        postId: Long,
        request: CreateCommentRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post")
        val authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal as UserPrincipal
        val member = memberRepository.findByIdOrNull(principal.id) ?: throw ModelNotFoundException("User")


        val comment = Comment(
            content = request.content,
            post = post,
            author = member
        )
        return commentRepository.save(comment).toResponse()

    }

    fun updateComment(
        postId: Long,
        commentId: Long,
        commentUpdateRequest: UpdateCommentRequest
    ): CommentResponse {
        postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post")
        val authentication = SecurityContextHolder.getContext().authentication
        authentication.principal as UserPrincipal
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment")

        return comment.toResponse()
    }

    fun deleteComment(postId: Long, commentId: Long) {
        postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post")
        commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment")
        commentRepository.deleteAll()
    }
}