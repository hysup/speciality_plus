package com.example.kotlin25.like.service

import com.example.kotlin25.config.sercurity.UserPrincipal
import com.example.kotlin25.global.Type.ModelNotFoundException
import com.example.kotlin25.like.model.Like
import com.example.kotlin25.like.repository.PostLikeRepository
import com.example.kotlin25.post.repository.PostRepository
import com.example.kotlin25.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class LikeService(
    private val postLikeRepository: PostLikeRepository,
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository
) {

    fun likePost(postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post")
        val authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal as UserPrincipal

        val member = memberRepository.findByNickname(principal.nickname)
            ?: throw ModelNotFoundException("Member")

        if (member.id != post.id) {
            throw ModelNotFoundException("User")
        }
        val like = Like(
            post = post,
            author = member
        )
        postLikeRepository.save(like)

    }

    fun removePostLike(postId: Long, likeId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post")

        val like = postLikeRepository.findByIdOrNull(likeId) ?: throw ModelNotFoundException("Like")
        postLikeRepository.delete(like)


    }
}