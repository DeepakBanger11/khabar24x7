package com.startlearning.khabar24x7.modal.dataStore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.startlearning.khabar24x7.modal.data.TableArticle
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.userPreferencesDataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context) {
    private val dataStore = context.userPreferencesDataStore

    val selectedLanguageFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_SELECTED_LANGUAGE]
    }

    val selectedCategoriesFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_SELECTED_CATEGORIES]
    }
    val email: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_EMAIL]
    }
    val password: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_PASSWORD]
    }
    val navigation: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_NAVIGATION]
    }
    val articleId: Flow<Int?> = dataStore.data.map { preferences ->
        preferences[KEY_ARTICLE_ID]
    }
    suspend fun setSelectedLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[KEY_SELECTED_LANGUAGE] = language
        }
    }
    suspend fun setArticle(article: TableArticle) {
        val articleString = convertTableArticleToString(article)
        dataStore.edit { preferences ->
            preferences[KEY_NEWS_DETAILS] = articleString
        }
    }

    suspend fun getArticle(): TableArticle? {
        val articleString = dataStore.data.firstOrNull()?.get(KEY_NEWS_DETAILS)
        return if (articleString != null) {
            convertStringToTableArticle(articleString)
        } else {
            null
        }
    }

    // Helper methods for serialization/deserialization
    private fun convertTableArticleToString(article: TableArticle): String {
        return Gson().toJson(article)
    }

    private fun convertStringToTableArticle(articleString: String): TableArticle? {
        return Gson().fromJson(articleString, TableArticle::class.java)

    }
    suspend fun toggleCategory(category: String) {
        dataStore.edit { preferences ->
            preferences[KEY_SELECTED_CATEGORIES] = category
        }
    }

    suspend fun setLogin(email: String,password:String) {
        dataStore.edit { preferences ->
            preferences[KEY_EMAIL] = email
        }
        dataStore.edit { preferences ->
            preferences[KEY_PASSWORD] = password
        }
    }
    suspend fun setNavigation(navigation: String) {
        dataStore.edit { preferences ->
            preferences[KEY_NAVIGATION] = navigation
        }
    }
    suspend fun setArticleId(articleId: Int) {
        dataStore.edit { preferences ->
            preferences[KEY_ARTICLE_ID] = articleId
        }
    }

    companion object {
        private val KEY_SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
        private val KEY_SELECTED_CATEGORIES = stringPreferencesKey("selected_categories")
        private val KEY_EMAIL = stringPreferencesKey("email")
        private val KEY_PASSWORD = stringPreferencesKey("password")
        private val KEY_NAVIGATION = stringPreferencesKey("navigation")
        private val KEY_ARTICLE_ID = intPreferencesKey("articleId")
        private val KEY_NEWS_DETAILS = stringPreferencesKey("news_details")


    }
}
