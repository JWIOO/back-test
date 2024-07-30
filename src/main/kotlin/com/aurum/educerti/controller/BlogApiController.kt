package com.aurum.educerti.controller

import com.aurum.educerti.domain.Article
import com.aurum.educerti.service.BlogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/articles")
class BlogApiController(private val blogService: BlogService) {

    @PostMapping
    fun addArticle(@RequestBody request: com.aurum.educerti.dto.AddArticleRequest): ResponseEntity<Article> {
        val savedArticle = blogService.save(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle)
    }

    @GetMapping
    fun findAllArticles(): ResponseEntity<List<com.aurum.educerti.dto.ArticleResponse>> {
        val articles = blogService.findAll().map { com.aurum.educerti.dto.ArticleResponse(it) }
        return ResponseEntity.ok(articles)
    }

    @GetMapping("/{id}")
    fun findArticle(@PathVariable id: Long): ResponseEntity<com.aurum.educerti.dto.ArticleResponse> {
        val article = blogService.findById(id)
        return ResponseEntity.ok(com.aurum.educerti.dto.ArticleResponse(article))
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<Void> {
        blogService.delete(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun updateArticle(
        @PathVariable id: Long,
        @RequestBody request: com.aurum.educerti.dto.UpdateArticleRequest
    ): ResponseEntity<Article> {
        val updatedArticle = blogService.update(id, request)
        return ResponseEntity.ok(updatedArticle)
    }
}
