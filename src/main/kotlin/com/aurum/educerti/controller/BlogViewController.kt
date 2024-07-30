package com.aurum.educerti.controller

import com.aurum.educerti.dto.ArticleListViewResponse
import com.aurum.educerti.dto.ArticleViewResponse
import com.aurum.educerti.service.BlogService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class BlogViewController(private val blogService: BlogService) {

    @GetMapping("/articles")
    fun getArticles(model: Model): String {
        val articles = blogService.findAll().map { ArticleListViewResponse(it) }
        model.addAttribute("articles", articles)
        return "articleList"
    }

    @GetMapping("/articles/{id}")
    fun getArticle(@PathVariable id: Long, model: Model): String {
        val article = blogService.findById(id)
        model.addAttribute("article", ArticleViewResponse(article))
        return "article"
    }

    @GetMapping("/new-article")
    fun newArticle(@RequestParam(required = false) id: Long?, model: Model): String {
        val articleViewResponse = if (id == null) {
            ArticleViewResponse()
        } else {
            val article = blogService.findById(id)
            ArticleViewResponse(article)
        }
        model.addAttribute("article", articleViewResponse)
        return "newArticle"
    }
}
