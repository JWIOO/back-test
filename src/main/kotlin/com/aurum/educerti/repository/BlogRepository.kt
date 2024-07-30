package com.aurum.educerti.repository

import com.aurum.educerti.domain.Article
import org.springframework.data.jpa.repository.JpaRepository

interface BlogRepository : JpaRepository<Article, Long>