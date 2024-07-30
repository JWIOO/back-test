package com.aurum.educerti.dto

import com.aurum.educerti.domain.Article

data class AddArticleRequest(
    val title: String?,
    val content: String?
) {
    fun toEntity(): Article {
        return Article(
            title = title ?: "",
            content = content ?: ""
        )
    }
}
