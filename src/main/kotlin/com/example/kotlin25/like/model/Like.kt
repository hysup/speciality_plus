package com.example.kotlin25.like.model

import com.example.kotlin25.post.model.Post
import com.example.kotlin25.member.repository.model.Member
import jakarta.persistence.*


@Entity
@Table(name = "likes")
class Like (

    @ManyToOne
    @JoinColumn(name ="post_id", nullable = false)
    val post: Post,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val author: Member,


    ){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}