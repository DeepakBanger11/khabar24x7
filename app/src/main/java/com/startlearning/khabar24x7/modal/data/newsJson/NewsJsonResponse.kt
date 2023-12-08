package com.startlearning.khabar24x7.modal.data.newsJson

data class NewsJsonResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)