package com.example.kotlin25.like.controller

import com.example.kotlin25.config.sercurity.UserPrincipal
import com.example.kotlin25.like.service.LikeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/likes")
@RestController
class PostLikeController(
    private val likeService: LikeService
) {
    @PostMapping("/post/{postId}")
    fun likePost(
        @PathVariable postId: Long,
        @RequestParam nickname: String,
    ): ResponseEntity<Unit> {
        likeService.likePost(postId)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build()

    }

    @DeleteMapping("/{likeId}/posts/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
        @PathVariable likeId: Long,
    ): ResponseEntity<Unit> {
        val authentication = SecurityContextHolder.getContext().authentication
        authentication.principal as UserPrincipal

        likeService.removePostLike(likeId, postId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()

    }
}