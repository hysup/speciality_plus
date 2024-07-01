package com.example.kotlin25.post.controller

import com.example.kotlin25.post.dto.CreatePostRequest
import com.example.kotlin25.post.dto.PostResponse
import com.example.kotlin25.post.dto.UpdatePostRequest
import com.example.kotlin25.post.service.PostService

import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/posts")
@RestController
class PostController(
    private val postService: PostService
) {
    @GetMapping("/search")
    fun searchPostList(@RequestParam(name = "title") title: String): ResponseEntity<List<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.searchPostList(title))
    }


    @PostMapping
    fun createPost(
        @Valid @RequestBody request: CreatePostRequest
    ): ResponseEntity<PostResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(request))

    @GetMapping()
    fun getAllPosts(
        @PageableDefault(size = 10, sort = ["id"]) pageable: Pageable,

    ): ResponseEntity<Page<PostResponse>> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPaginatedPostList(pageable))
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long,
    ): ResponseEntity<PostResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(postService.getPost(postId))

    }

    @PutMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long ,@RequestBody request: UpdatePostRequest): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(postId, request))
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Unit> =

         ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(postService.deletePost(postId))

    }


