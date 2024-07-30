package com.aurum.educerti.dto

import com.aurum.educerti.domain.Article
import java.time.LocalDateTime

data class ArticleViewResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime
) {
    constructor() : this(0, "", "", LocalDateTime.now())

    constructor(article: Article) : this(
        id = article.id ?: 0,
        title = article.title,
        content = article.content,
        createdAt = article.createdAt ?: LocalDateTime.now()
    )
}
