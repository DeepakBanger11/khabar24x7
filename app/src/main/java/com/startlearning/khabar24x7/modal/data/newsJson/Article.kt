package com.startlearning.khabar24x7.modal.data.newsJson

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.startlearning.khabar24x7.utils.Constants.DATABASE_TABLE

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)