package com.example.kotlin25.comment.model

import com.example.kotlin25.comment.dto.CommentResponse
import com.example.kotlin25.post.model.Post
import com.example.kotlin25.member.repository.model.Member
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "comments")
class Comment (

    @Column(name = "content")
    var content: String,

    @ManyToOne
    @JoinColumn(name ="post_id", nullable = false)
    val post: Post,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val author: Member,

    @Column(nullable = false, updatable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now()

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toResponse(): CommentResponse {
        return CommentResponse(
            id = this.id!!,
            content = this.content,
            createdAt = this.createdAt,
            postId = this.post.id!!,

        )
    }


}