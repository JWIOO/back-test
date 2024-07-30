package com.aurum.educerti.dto

import com.aurum.educerti.domain.Article

data class ArticleResponse(
    val title: String,
    val content: String
) {
    constructor(article: Article) : this(
        title = article.title,
        content = article.content
    )
}
