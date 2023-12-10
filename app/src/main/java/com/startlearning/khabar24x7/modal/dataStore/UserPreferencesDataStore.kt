package com.startlearning.khabar24x7.modal.dataStore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
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
    suspend fun setSelectedLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[KEY_SELECTED_LANGUAGE] = language
        }
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

    companion object {
        private val KEY_SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
        private val KEY_SELECTED_CATEGORIES = stringPreferencesKey("selected_categories")
        private val KEY_EMAIL = stringPreferencesKey("email")
        private val KEY_PASSWORD = stringPreferencesKey("password")
        private val KEY_NAVIGATION = stringPreferencesKey("navigation")
    }
}
