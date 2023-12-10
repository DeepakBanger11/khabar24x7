package com.startlearning.khabar24x7.modal.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.startlearning.khabar24x7.modal.data.TableArticle
import com.startlearning.khabar24x7.modal.data.newsJson.Article

@Database(entities = [TableArticle::class], version = 1, exportSchema = false)
abstract class NewsDatabase :RoomDatabase() {
    abstract fun newsDao(): NewsDao
}