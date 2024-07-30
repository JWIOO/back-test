package com.aurum.educerti.service

import com.aurum.educerti.domain.Article
import com.aurum.educerti.repository.BlogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BlogService(
    private val blogRepository: BlogRepository
) {

    fun save(request: com.aurum.educerti.dto.AddArticleRequest): Article {
        return blogRepository.save(request.toEntity())
    }

    fun findAll(): List<Article> {
        return blogRepository.findAll()
    }

    fun findById(id: Long): Article {
        return blogRepository.findById(id)
            .orElseThrow { IllegalArgumentException("not found: $id") }
    }

    fun delete(id: Long) {
        blogRepository.deleteById(id)
    }

    @Transactional
    fun update(id: Long, request: com.aurum.educerti.dto.UpdateArticleRequest): Article {
        val article = blogRepository.findById(id)
            .orElseThrow { IllegalArgumentException("not found: $id") }
        article.update(request.title ?: "", request.content ?: "")
        return article
    }
}
