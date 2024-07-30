package com.aurum.educerti.dto

import com.aurum.educerti.domain.Article

data class ArticleListViewResponse(
    val id: Long,
    val title: String,
    val content: String
) {
    constructor(article: Article) : this(
        id = article.id ?: 0,
        title = article.title,
        content = article.content
    )
}
