package com.startlearning.khabar24x7.modal.network

import com.startlearning.khabar24x7.modal.data.newsJson.NewsJsonResponse
import com.startlearning.khabar24x7.utils.ApiKey.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiServices {

    @GET("everything?apiKey=$API_KEY") // Endpoint for fetching plants
    suspend fun getAllNews(
        @Query("page") page: Int,
        @Query("q") category: String
    ): NewsJsonResponse
}
