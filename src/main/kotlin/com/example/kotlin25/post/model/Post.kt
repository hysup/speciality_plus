package com.example.kotlin25.post.model

import com.example.kotlin25.comment.model.Comment
import com.example.kotlin25.like.model.Like
import com.example.kotlin25.post.dto.PostResponse
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "nickname")
    var nickname: String,

    @Column(nullable = false, updatable = false)
    var createdAt : ZonedDateTime,

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val comments: List<Comment> = mutableListOf(),


){
    fun toResponse() : PostResponse {
        return PostResponse(
            id = this.id,
            title = this.title,
            content = this.content,
            nickname = this.nickname,
            createdAt = this.createdAt,
            comments = emptyList()
        )

    }
    fun toResponseWithComment() : PostResponse {
        return PostResponse(
            id = this.id,
            title = this.title,
            content = this.content,
            nickname = this.nickname,
            createdAt = this.createdAt,
            comments = this.comments.map { it.toResponse() }
        )

    }


}
