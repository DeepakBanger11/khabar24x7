package com.startlearning.khabar24x7.modal.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.startlearning.khabar24x7.modal.data.newsJson.Article
import com.startlearning.khabar24x7.modal.data.newsJson.NewsJsonResponse
import com.startlearning.khabar24x7.modal.network.NewsApiServices
import com.startlearning.khabar24x7.modal.network.paging.NewsPagingSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
open
class NewsRepository @Inject constructor(
    private val newsApiServices: NewsApiServices
) {
    suspend fun getAllNews(page:Int,category: String,language:String): NewsJsonResponse {
        return newsApiServices.getAllNews(page,category,language)
    }
    fun getNewsPaging(category: String,language:String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = { NewsPagingSource(newsApiServices,category,language) }
        ).flow
    }

}