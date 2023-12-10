package com.startlearning.khabar24x7.modal.viewModal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.startlearning.khabar24x7.modal.data.newsJson.Article
import com.startlearning.khabar24x7.modal.data.newsJson.NewsJsonResponse
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val userPreferencesDataStore: UserPreferencesDataStore
) : ViewModel() {

    private val _newsJsonResponse = MutableLiveData<NewsJsonResponse>()
    val newsJsonResponse: LiveData<NewsJsonResponse> = _newsJsonResponse


    suspend fun <T> apiRequestWithRetry(
        retryCount: Int = 3,
        initialDelayMillis: Long = 1000L,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelayMillis
        repeat(retryCount) { attempt ->
            try {
                return block()
            } catch (e: HttpException) {
                if (e.code() == 429) {
                    // Exponential backoff: Increase delay exponentially between retries
                    delay(currentDelay)
                    currentDelay *= 2
                } else {
                    throw e
                }
            }
        }
        throw IOException("Failed after $retryCount attempts")
    }

    fun getAllNews(page: Int, category: String,language:String) {
        try {
            viewModelScope.launch {
                val response = apiRequestWithRetry { repository.getAllNews(page, category,language) }
                _newsJsonResponse.postValue(response)
            }
        } catch (e: IOException) {

        }
    }

    fun getNewsPaging(category: String,language:String): Flow<PagingData<Article>> {
        return repository.getNewsPaging(category,language)
    }

    // Example function in the ViewModel to set the selected language/categories
    fun setSelectedLanguage(language: String) {
        viewModelScope.launch {
            userPreferencesDataStore.setSelectedLanguage(language)
        }
    }
    fun toggleCategory(category: String) {
        viewModelScope.launch {
            userPreferencesDataStore.toggleCategory(category)
        }
    }
    fun setLogin(email: String,password:String) {
        viewModelScope.launch {
            userPreferencesDataStore.setLogin(email,password)
        }
    }
    fun setNavigation(navigation: String){
        viewModelScope.launch {
            userPreferencesDataStore.setNavigation(navigation)
        }
    }


}