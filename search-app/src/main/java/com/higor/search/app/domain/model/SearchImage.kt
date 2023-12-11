package com.higor.search.app.domain.model

data class SearchImage(
    val title: String,
    val imageUrl: String,
    val descriptionText: String,
    val descriptionHTML: String,
    val author: String,
    val authorId: String,
    val tags: String,
    val width: Int,
    val height: Int
)
