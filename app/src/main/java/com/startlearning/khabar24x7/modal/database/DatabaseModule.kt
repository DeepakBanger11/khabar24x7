package com.startlearning.khabar24x7.modal.database

import android.content.Context
import androidx.room.Room
import com.startlearning.khabar24x7.modal.database.room.NewsDatabase
import com.startlearning.khabar24x7.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDao(database: NewsDatabase) = database.newsDao()
}
