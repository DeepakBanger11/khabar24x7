package com.startlearning.khabar24x7.modal.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.startlearning.khabar24x7.modal.data.newsJson.Article
import com.startlearning.khabar24x7.modal.network.NewsApiServices


class NewsPagingSource(
    private val apiServices: NewsApiServices,
    private val category: String,
    private val language:String
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPage = params.key ?: 1
            val response = apiServices.getAllNews(nextPage,category,language)
            LoadResult.Page(
                data = response.articles,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
