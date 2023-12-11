package com.startlearning.khabar24x7.modal.network

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsNetworkModule {
    val gson = GsonBuilder().setLenient().create()
    @Provides
    @Singleton
    fun networkRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun newsAPI(retrofit: Retrofit): NewsApiServices {
        val newsAPI = retrofit.create(NewsApiServices::class.java)
        return newsAPI
    }


}

