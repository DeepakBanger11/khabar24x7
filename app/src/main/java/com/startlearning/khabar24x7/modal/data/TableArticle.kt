package com.startlearning.khabar24x7.modal.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.startlearning.khabar24x7.modal.data.newsJson.Source
import com.startlearning.khabar24x7.utils.Constants

@Entity(tableName = Constants.DATABASE_TABLE)
data class TableArticle(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    //val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)
