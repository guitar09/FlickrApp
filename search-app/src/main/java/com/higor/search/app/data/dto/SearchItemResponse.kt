package com.higor.search.app.data.dto

data class SearchItemResponse(
    val title: String,
    val link: String,
    val media: SearchMediaResponse,
    val date_taken: String,
    val description: String,
    val published: String,
    val author: String,
    val author_id: String,
    val tags: String
)
