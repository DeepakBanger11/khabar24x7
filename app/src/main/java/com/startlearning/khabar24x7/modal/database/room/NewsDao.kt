package com.startlearning.khabar24x7.modal.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.startlearning.khabar24x7.modal.data.TableArticle


@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table Order BY id ASC")
    fun getAllArticles(): LiveData<List<TableArticle>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addArticles(article: TableArticle)

    @Query("SELECT * FROM news_table WHERE id =:articleId")
    fun getSelectedArticle(articleId: Int): LiveData<TableArticle>

}