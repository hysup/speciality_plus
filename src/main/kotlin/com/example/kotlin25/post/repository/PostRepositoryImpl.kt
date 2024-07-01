package com.example.kotlin25.post.repository

import com.example.kotlin25.config.querydsl.QueryDslSupport
import com.example.kotlin25.post.model.Post
import com.example.kotlin25.post.model.QPost
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class PostRepositoryImpl:CustomPostRepository, QueryDslSupport() {
    private val post = QPost.post
    override fun searchByPostListByTitle(title: String): List<Post> {
        return queryFactory.selectFrom(post) // queryFactory를 사용하여 QueryDSL 쿼리를 시작
            .where(post.title.containsIgnoreCase(title)) // 제목에 대해 대소문자를 무시하고 지정된 문자열이 포함된 경우 필터링
            .fetch() // 쿼리를 실행
    }

    override fun findByPageable(pageable: Pageable): Page<Post> {

        val totalCount = queryFactory.select(post.count()).fetchOne() ?: 0L
        val query = queryFactory.select(post)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        if (pageable.sort.isSorted) {
            when(pageable.sort.first()?.property) {
                "id" -> query.orderBy(post.id.asc())
                "title" -> query.orderBy(post.title.asc())
                else -> query.orderBy(post.id.asc())
            }
            }else {
                query.orderBy(post.id.asc())
            }
        val contents = query.fetch()

        return PageImpl(contents, pageable, totalCount)
    }
}