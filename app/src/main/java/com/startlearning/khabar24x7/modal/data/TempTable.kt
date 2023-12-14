package com.startlearning.khabar24x7.modal.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.startlearning.khabar24x7.utils.Constants

@Entity(tableName = Constants.TEMP_DATABASE_TABLE)
data class TempTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    //val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)