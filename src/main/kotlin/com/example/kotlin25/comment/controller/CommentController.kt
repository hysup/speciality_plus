package com.example.kotlin25.comment.controller

import com.example.kotlin25.comment.dto.CommentResponse
import com.example.kotlin25.comment.dto.CreateCommentRequest
import com.example.kotlin25.comment.dto.UpdateCommentRequest
import com.example.kotlin25.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1/post/{postId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {


    @PostMapping
    fun createComment(
        @PathVariable postId: Long,
        @RequestBody request: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(postId, request))
    }

        @PutMapping("/{commentId}")
        fun updateComment(
            @PathVariable postId: Long,
            @PathVariable commentId: Long,
            @RequestBody request: UpdateCommentRequest
        ): ResponseEntity<CommentResponse> {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.updateComment(postId, commentId, request))
        }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable postId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<Unit> =

        ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(commentService.deleteComment(postId, commentId))

}

